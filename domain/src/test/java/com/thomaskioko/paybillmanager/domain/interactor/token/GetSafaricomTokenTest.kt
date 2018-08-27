package com.thomaskioko.paybillmanager.domain.interactor.token

import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.domain.repository.TokenRepository
import com.thomaskioko.paybillmanager.domain.token.GetSafaricomToken
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetSafaricomTokenTest {

    private lateinit var getSafaricomToken: GetSafaricomToken

    @Mock
    lateinit var tokenRepository: TokenRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getSafaricomToken = GetSafaricomToken(tokenRepository, postExecutionThread)
    }

    @Test
    fun getSafaricomTokenCompletes() {
        stubGetSafaricomTokenRepository(Observable.just(TestDataFactory.makeSafaricomToken()))

        val testObserver = getSafaricomToken.buildUseCaseObservable().test()
        testObserver.assertComplete()
    }

    @Test
    fun getSafaricomTokenReturnsData() {
        val token = TestDataFactory.makeSafaricomToken()

        //Stub the repository
        stubGetSafaricomTokenRepository(Observable.just(token))

        val testObserver = getSafaricomToken.buildUseCaseObservable().test()

        //Verify that the data returned is what is expected
        testObserver.assertValue(token)
    }


    private fun stubGetSafaricomTokenRepository(observable: Observable<SafaricomToken>) {
        whenever(tokenRepository.getToken()).thenReturn(observable)
    }

}