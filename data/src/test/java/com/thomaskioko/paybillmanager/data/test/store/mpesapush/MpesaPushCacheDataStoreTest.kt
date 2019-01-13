package com.thomaskioko.paybillmanager.data.test.store.mpesapush

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushCache
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushCacheDataStoreTest {

    private val cache = mock<MpesaPushCache>()
    private val store = MpesaPushCacheDataStore(cache)

    @Test
    fun createMpesaPushRequestCompletes() {
        stubGetMpesaStkPushRequest(Flowable.just(DataFactory.makeMpesaPushResponseEntity()))

        val testObserver = store.getMpesaStkPushRequest(DataFactory.makeMpesaPushRequest()).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMpesaPushResponseCompletes() {
        stubSaveMpesaRequestResponse(Completable.complete())

        val testObserver = store.saveMpesaPushResponse(DataFactory.makeMpesaPushResponseEntity()).test()
        testObserver.assertComplete()
    }

    private fun stubSaveMpesaRequestResponse(completable: Completable) {
        whenever(cache.saveMpesaPushResponse(any()))
                .thenReturn(completable)
    }

    private fun stubGetMpesaStkPushRequest(completable: Flowable<MpesaPushResponseEntity>) {
        whenever(cache.getMpesaStkPushRequest(any()))
                .thenReturn(completable)
    }

}