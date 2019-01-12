package com.thomaskioko.paybillmanager.domain.interactor.mpesarequest

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.factory.TestDataFactory
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.repository.MpesaRequestRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CreateMpesaPushResponseRequestTest {

    private lateinit var getMpesaStkPush: GetMpesaStkPush
    @Mock
    lateinit var repository: MpesaRequestRepository
    @Mock
    lateinit var postExecutionThread: PostExecutionThread
    @Mock
    private lateinit var mockThreadExecutor: ThreadExecutor

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getMpesaStkPush = GetMpesaStkPush(repository, mockThreadExecutor, postExecutionThread)
    }

    @Test
    fun createMpesaPushRequestCompletes() {
        stubMpesaPushRequestRepository(Flowable.just(TestDataFactory.makeJengaMpesaPushResponse()))

        val testObserver = getMpesaStkPush.buildUseCaseObservable(
                GetMpesaStkPush.Params.forGetMpesaPushRequest(TestDataFactory.makeMpesaPushRequest())
        ).test()

        //Verify that the observable completes
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun createMpesaPushRequestThrowsException() {
        getMpesaStkPush.buildUseCaseObservable().test()
    }


    private fun stubMpesaPushRequestRepository(completable: Flowable<MpesaPushResponse>) {
        whenever(repository.getMpesaStkPush(any())).thenReturn(completable)
    }
}
