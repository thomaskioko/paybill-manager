package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.domain.token.GetSafaricomToken
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.factory.TokenFactory
import com.thomaskioko.paybillmanager.presentation.mapper.TokenViewMapper
import com.thomaskioko.paybillmanager.presentation.model.TokenView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class GetTokenViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Captor
    val captor = argumentCaptor<DisposableObserver<SafaricomToken>>()

    private var getSafaricomToken = mock<GetSafaricomToken>()
    private var mapper = mock<TokenViewMapper>()
    private var viewModel = GetTokenViewModel(getSafaricomToken, mapper)


    @Test
    fun fetchTokenExecutesUseCase() {
        viewModel.fetchToken()

        //verify that fetch bill by id use case is called once
        verify(getSafaricomToken, times(1)).execute(any(), eq(null))
    }


    @Test
    fun fetchTokenReturnsSuccess() {
        val safaricomToken = TokenFactory.makeSafaricomToken()
        val tokenView = TokenFactory.makeSafaricomTokenView()

        stubTokenMapperMapToView(tokenView, safaricomToken)

        //invoke fetch fetchToken
        viewModel.fetchToken()

        //Use captor to capture the response when execute is called
        verify(getSafaricomToken).execute(captor.capture(), eq( null))

        //Pass data to onNext callback
        captor.firstValue.onNext(safaricomToken)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, viewModel.getToken().value?.status)
    }


    @Test
    fun fetchTokenReturnsData() {
        val safaricomToken = TokenFactory.makeSafaricomToken()
        val tokenView = TokenFactory.makeSafaricomTokenView()

        stubTokenMapperMapToView(tokenView, safaricomToken)

        //invoke fetch fetchToken
        viewModel.fetchToken()

        //Use captor to capture the response when execute is called
        verify(getSafaricomToken).execute(captor.capture(), eq(null))


        //Pass data to onNext callback
        captor.firstValue.onNext(safaricomToken)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(tokenView, viewModel.getToken().value?.data)
    }


    @Test
    fun fetchTokenReturnsError() {
        //invoke fetch fetchToken
        viewModel.fetchToken()

        //Use captor to capture the response when execute is called
        verify(getSafaricomToken).execute(captor.capture(), eq(null))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getToken().value?.status)
    }


    @Test
    fun fetchTokenReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch fetchToken
        viewModel.fetchToken()

        //Use captor to capture the response when execute is called
        verify(getSafaricomToken).execute(captor.capture(), eq(null))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getToken().value?.message)
    }

    private fun stubTokenMapperMapToView(tokenView: TokenView, project: SafaricomToken) {
        whenever(mapper.mapToView(project)).thenReturn(tokenView)
    }


}