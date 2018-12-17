package com.thomaskioko.paybillmanager.data.test.store.jengatoken

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class JengaTokenCacheDataStoreTest {

    private val cache = mock<JengaTokenCache>()
    private val store = JengaTokenCacheDataStore(cache)

    @Test
    fun getTokenCompletes() {
        stubTokenCacheGetToken(Flowable.just(DataFactory.makeJengaTokenEntity()))

        val testObserver = store.getJengaToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val data = DataFactory.makeJengaTokenEntity()
        stubTokenCacheGetToken(Flowable.just(data))

        val testObserver = store.getJengaToken().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getTokenCallsCacheStore() {
        stubTokenCacheGetToken(Flowable.just(DataFactory.makeJengaTokenEntity()))
        stubCacheSetExpiryTime(Completable.complete())

        store.getJengaToken().test()
        verify(cache).getJengaToken()
    }

    @Test
    fun saveTokenCompletes() {
        stubSaveToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        val testObservable = store.saveJengaToken(DataFactory.makeJengaTokenEntity()).test()
        testObservable.assertComplete()
    }

    @Test
    fun saveTokenCallsCache() {
        val data = DataFactory.makeJengaTokenEntity()
        stubSaveToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        store.saveJengaToken(data).test()
        verify(cache).saveJengaToken(data)
    }

    @Test
    fun clearToken() {
        stubClearToken(Completable.complete())

        val testObservable = store.clearJengaToken().test()
        testObservable.assertComplete()

    }

    @Test
    fun clearTokenCallsCacheStore() {
        stubClearToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        store.clearJengaToken().test()
        verify(cache).clearJengaToken()

    }

    private fun stubTokenCacheGetToken(observable: Flowable<JengaTokenEntity>) {
        whenever(cache.getJengaToken()).thenReturn(observable)
    }

    private fun stubSaveToken(completable: Completable) {
        whenever(cache.saveJengaToken(any()))
                .thenReturn(completable)
    }

    private fun stubClearToken(completable: Completable) {
        whenever(cache.clearJengaToken()).thenReturn(completable)
    }


    private fun stubCacheSetExpiryTime(completable: Completable) {
        whenever(cache.setExpireTime(any()))
                .thenReturn(completable)
    }

}