package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.jengatoken.GetJengaToken
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.factory.TokenFactory
import com.thomaskioko.paybillmanager.presentation.mapper.JengaTokenViewMapper
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class JengaRequestsViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Captor
    val captor = argumentCaptor<DisposableObserver<JengaToken>>()

    private var getJengaToken = mock<GetJengaToken>()
    private var mapper = mock<JengaTokenViewMapper>()
    private var viewModel = JengaRequestsViewModel(getJengaToken, mapper)


    @Test
    fun fetchJengaTokenExecutesUseCase() {
        viewModel.fetchJengaToken()

        //verify that fetch bill by id use case is called once
        verify(getJengaToken, times(1)).execute(any(), eq(null))
    }


    @Test
    fun fetchJengaTokenReturnsSuccess() {
        val jengaToken = TokenFactory.makeJengaToken()
        val tokenView = TokenFactory.makeJengaTokenView()

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
        val jengaToken = TokenFactory.makeJengaToken()
        val tokenView = TokenFactory.makeJengaTokenView()

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
        verify(getJengaToken).execute(captor.capture(),  eq(null))


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
        verify(getJengaToken).execute(captor.capture(),  eq(null))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getJengaToken().value?.message)
    }

    private fun stubTokenMapperMapToView(tokenView: JengaTokenView, jengaToken: JengaToken) {
        whenever(mapper.mapToView(jengaToken)).thenReturn(tokenView)
    }


}