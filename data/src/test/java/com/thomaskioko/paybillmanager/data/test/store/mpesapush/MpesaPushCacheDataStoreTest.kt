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
        stubGetMpesaStkPushRequest(Flowable.just(listOf(DataFactory.makeMpesaPushResponseEntity())))

        val testObserver = store.getMpesaStkPushRequests().test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMpesaPushResponseCompletes() {
        stubSaveMpesaRequestResponse(Completable.complete())

        val testObserver = store.saveMpesaPushResponse(DataFactory.makeMpesaPushResponseEntity()).test()
        testObserver.assertComplete()
    }

    @Test
    fun clearMpesaPushRequestsCompletes() {
        stubClearMpesaRequestResponse(Completable.complete())

        val testObserver = store.clearMpesaPushRequests().test()
        testObserver.assertComplete()
    }

    @Test(expected = UnsupportedOperationException::class)
    fun getMpesaStkPushRequest() {
        val entity = DataFactory.makeMpesaPushRequest()

        val testObserver = store.getMpesaStkPushRequest("er", "er", entity).test()
        testObserver.assertComplete()
    }

    @Test
    fun isStkResponseCachedCompletes() {
        stubIsStkResponseCached(Single.just(false))

        val testObserver = store.isStkResponseCached("123").test()
        testObserver.assertComplete()
    }

    private fun stubSaveMpesaRequestResponse(completable: Completable) {
        whenever(cache.saveMpesaPushResponse(any()))
                .thenReturn(completable)
    }

    private fun stubClearMpesaRequestResponse(completable: Completable) {
        whenever(cache.clearMpesaPushRequests())
                .thenReturn(completable)
    }

    private fun stubIsStkResponseCached(completable: Single<Boolean>) {
        whenever(cache.isStkResponseCached(any()))
                .thenReturn(completable)
    }

    private fun stubGetMpesaStkPushRequest(flowable: Flowable<List<MpesaPushResponseEntity>>) {
        whenever(cache.getMpesaStkPushRequests())
                .thenReturn(flowable)
    }

}