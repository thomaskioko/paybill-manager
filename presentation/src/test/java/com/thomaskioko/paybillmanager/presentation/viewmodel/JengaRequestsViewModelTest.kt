package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.jengatoken.GetJengaToken
import com.thomaskioko.paybillmanager.domain.interactor.mpesarequest.GetMpesaStkPush
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.factory.JengaFactory
import com.thomaskioko.paybillmanager.presentation.mapper.JengaTokenViewMapper
import com.thomaskioko.paybillmanager.presentation.mapper.MpesaPushViewMapper
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.model.MpesaPushResponseView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Captor


@RunWith(JUnit4::class)
class JengaRequestsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Captor
    val captor = argumentCaptor<DisposableSubscriber<JengaToken>>()

    @Captor
    val mpesaCaptor = argumentCaptor<DisposableSubscriber<MpesaPushResponse>>()

    private var getJengaToken = mock<GetJengaToken>()
    private var getMpesaStkPush = mock<GetMpesaStkPush>()
    private var mapper = mock<JengaTokenViewMapper>()
    private var mpesaPushViewMapper = mock<MpesaPushViewMapper>()
    private var viewModel = JengaRequestsViewModel(getJengaToken, getMpesaStkPush, mapper, mpesaPushViewMapper)


    @Test
    fun fetchJengaTokenExecutesUseCase() {
        viewModel.fetchJengaToken()

        //verify that fetch bill by id use case is called once
        verify(getJengaToken, times(1)).execute(any(), eq(null))
    }


    @Test
    fun fetchJengaTokenReturnsSuccess() {
        val jengaToken = JengaFactory.makeJengaToken()
        val tokenView = JengaFactory.makeJengaTokenView()

        stubTokenMapperMapToView(tokenView, jengaToken)

        //invoke fetch fetchJengaToken
        viewModel.fetchJengaToken()

        //Use captor to capture the response when execute is called
        verify(getJengaToken).execute(captor.capture(), eq(null))

        //Pass data to onNext callback
        captor.firstValue.onNext(jengaToken)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, viewModel.getJengaToken().value?.status)
    }


    @Test
    fun fetchJengaTokenReturnsData() {
        val jengaToken = JengaFactory.makeJengaToken()
        val tokenView = JengaFactory.makeJengaTokenView()

        stubTokenMapperMapToView(tokenView, jengaToken)

        viewModel.fetchJengaToken()

        //invoke fetch fetchJengaToken
        verify(getJengaToken).execute(captor.capture(), eq(null))


        //Pass data to onNext callback
        captor.firstValue.onNext(jengaToken)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(tokenView, viewModel.getJengaToken().value?.data)
    }


    @Test
    fun fetchJengaTokenReturnsError() {
        //invoke fetch fetchJengaToken
        viewModel.fetchJengaToken()

        //Use captor to capture the response when execute is called
        verify(getJengaToken).execute(captor.capture(), eq(null))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getJengaToken().value?.status)
    }


    @Test
    fun fetchJengaTokenReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch fetchJengaToken
        viewModel.fetchJengaToken()

        //Use captor to capture the response when execute is called
        verify(getJengaToken).execute(captor.capture(), eq(null))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getJengaToken().value?.message)
    }

    @Test
    fun fetchMpesaPushResponseExecutesUseCase() {

        val request = JengaFactory.makeMpesaPushRequest()

        //invoke fetch fetchJengaToken
        viewModel.fetchMpesaPushResponse("Bearer: ", request)

        //verify that fetch bill by id use case is called once
        verify(getMpesaStkPush, times(1)).execute(any(), eq(
                GetMpesaStkPush.Params.forGetMpesaPushRequest("Bearer: ",  request)
        ))
    }

    @Test
    fun fetchMpesaPushResponseReturnsSuccess() {
        val model = JengaFactory.makeMpesaPushResponse()
        val view = JengaFactory.makeMpesaPushResponseView()
        val request = JengaFactory.makeMpesaPushRequest()

        stubMpesaPushViewMapperMapToView(view, model)

        //invoke fetch fetchJengaToken
        viewModel.fetchMpesaPushResponse("Bearer: ", request)

        verify(getMpesaStkPush).execute(mpesaCaptor.capture(), eq(
                GetMpesaStkPush.Params.forGetMpesaPushRequest("Bearer: ", request)
        ))

        //Pass data to onNext callback
        mpesaCaptor.firstValue.onNext(model)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, viewModel.getMpesaPushResponse().value?.status)
    }

    @Test
    fun fetchMpesaPushResponseReturnsData() {
        val model = JengaFactory.makeMpesaPushResponse()
        val view = JengaFactory.makeMpesaPushResponseView()
        val request = JengaFactory.makeMpesaPushRequest()

        stubMpesaPushViewMapperMapToView(view, model)

        //invoke fetch fetchMpesaPushResponse
        viewModel.fetchMpesaPushResponse("Bearer: ", request)

        verify(getMpesaStkPush).execute(mpesaCaptor.capture(), eq(
                GetMpesaStkPush.Params.forGetMpesaPushRequest("Bearer: ", request)
        ))

        //Pass data to onNext callback
        mpesaCaptor.firstValue.onNext(model)

        //Verify that the data returned is what is returned
        assertEquals(view, viewModel.getMpesaPushResponse().value?.data)
    }

    @Test
    fun fetchMpesaPushResponseReturnsError() {
        val request = JengaFactory.makeMpesaPushRequest()
        //invoke fetch fetchJengaToken
        viewModel.fetchMpesaPushResponse("Bearer: ", request)

        //Use captor to capture the response when execute is called
        verify(getMpesaStkPush).execute(mpesaCaptor.capture(), eq(
                GetMpesaStkPush.Params.forGetMpesaPushRequest("Bearer: ", request)
        ))


        //Pass Exception to onError callback
        mpesaCaptor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getMpesaPushResponse().value?.status)
    }

    @Test
    fun fetchMpesaPushResponseReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()
        val request = JengaFactory.makeMpesaPushRequest()
        //invoke fetch fetchJengaToken
        viewModel.fetchMpesaPushResponse("Bearer: ", request)

        //Use captor to capture the response when execute is called
        verify(getMpesaStkPush).execute(mpesaCaptor.capture(), eq(
                GetMpesaStkPush.Params.forGetMpesaPushRequest("Bearer: ", request)
        ))


        //Pass error message to onError callback
        mpesaCaptor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getMpesaPushResponse().value?.message)
    }


    private fun stubTokenMapperMapToView(tokenView: JengaTokenView, jengaToken: JengaToken) {
        whenever(mapper.mapToView(jengaToken)).thenReturn(tokenView)
    }

    private fun stubMpesaPushViewMapperMapToView(view: MpesaPushResponseView, model: MpesaPushResponse) {
        whenever(mpesaPushViewMapper.mapToView(model)).thenReturn(view)
    }


}