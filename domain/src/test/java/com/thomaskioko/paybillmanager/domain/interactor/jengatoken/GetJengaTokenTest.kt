package com.thomaskioko.paybillmanager.domain.interactor.jengatoken

import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetJengaTokenTest {

    private lateinit var getJengaToken: GetJengaToken

    @Mock
    lateinit var repository: JengaTokenRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getJengaToken = GetJengaToken(repository, postExecutionThread)
    }

    @Test
    fun getJengaTokenCompletes() {
        stubGetJengaTokenRepository(Observable.just(TestDataFactory.makeJengaToken()))

        val testObservable = getJengaToken.buildUseCaseObservable().test()

        //Verify that task completes
        testObservable.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        stubGetJengaTokenRepository(Observable.just(TestDataFactory.makeJengaToken()))

        val jengaToken = TestDataFactory.makeJengaToken()

        val testObservable = getJengaToken.buildUseCaseObservable().test()

        testObservable.assertValue(jengaToken)

    }

    private fun stubGetJengaTokenRepository(observable: Observable<JengaToken>) {
        whenever(repository.getJengaToken()).thenReturn(observable)
    }
}