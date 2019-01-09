package com.thomaskioko.paybillmanager.domain.interactor.mpesarequest

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.JengaMpesaPushResponse
import com.thomaskioko.paybillmanager.domain.repository.MpesaRequestRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateJengaMpesaPushResponseRequestTest {

    private lateinit var createMpesaPushRequest: CreateMpesaPushRequest
    @Mock
    lateinit var repository: MpesaRequestRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        createMpesaPushRequest = CreateMpesaPushRequest(repository, mockThreadExecutor, postExecutionThread)
    }

    @Test
    fun createMpesaPushRequestCompletes() {
        stubMpesaPushRequestRepository(Flowable.just(TestDataFactory.makeJengaMpesaPushResponse()))

        val testObserver = createMpesaPushRequest.buildUseCaseObservable(
                CreateMpesaPushRequest.Params.forCreateMpesaPush(TestDataFactory.makeMpesaPushRequest())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun createMpesaPushRequestThrowsException() {
        createMpesaPushRequest.buildUseCaseObservable().test()
    }


    private fun stubMpesaPushRequestRepository(completable: Flowable<JengaMpesaPushResponse>) {
        whenever(repository.createMpesaPushRequest(any())).thenReturn(completable)
    }
}
