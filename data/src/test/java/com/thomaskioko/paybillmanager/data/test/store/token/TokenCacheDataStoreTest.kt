package com.thomaskioko.paybillmanager.data.test.store.token

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import com.thomaskioko.paybillmanager.data.store.token.TokenCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.TokenDataFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TokenCacheDataStoreTest {

    private val cache = mock<TokenCache>()
    private val store = TokenCacheDataStore(cache)

    @Test
    fun getTokenCompletes() {
        stubTokenCacheGetToken(Flowable.just(TokenDataFactory.makeSafaricomTokenEntity()))

        val testObserver = store.getSafaricomToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val data = TokenDataFactory.makeSafaricomTokenEntity()
        stubTokenCacheGetToken(Flowable.just(data))

        val testObserver = store.getSafaricomToken().test()
        testObserver.assertValue(data)
    }

    @Test
    fun getTokenCallsCacheStore() {
        stubTokenCacheGetToken(Flowable.just(TokenDataFactory.makeSafaricomTokenEntity()))
        stubCacheSetExpiryTime(Completable.complete())

        store.getSafaricomToken().test()
        verify(cache).getSafaricomToken()
    }

    @Test
    fun saveTokenCompletes() {
        stubSaveToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        val testObservable = store.saveSafaricomToken(TokenDataFactory.makeSafaricomTokenEntity()).test()
        testObservable.assertComplete()
    }

    @Test
    fun saveTokenCallsCache() {
        val data = TokenDataFactory.makeSafaricomTokenEntity()
        stubSaveToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        store.saveSafaricomToken(data).test()
        verify(cache).saveSafaricomToken(data)
    }

    @Test
    fun clearToken() {
        stubClearToken(Completable.complete())

        val testObservable = store.clearSafaricomToken().test()
        testObservable.assertComplete()

    }

    @Test
    fun clearTokenCallsCacheStore() {
        stubClearToken(Completable.complete())
        stubCacheSetExpiryTime(Completable.complete())

        store.clearSafaricomToken().test()
        verify(cache).clearSafaricomToken()

    }

    private fun stubTokenCacheGetToken(observable: Flowable<SafaricomTokenEntity>) {
        whenever(cache.getSafaricomToken()).thenReturn(observable)
    }

    private fun stubSaveToken(completable: Completable) {
        whenever(cache.saveSafaricomToken(any()))
                .thenReturn(completable)
    }

    private fun stubClearToken(completable: Completable) {
        whenever(cache.clearSafaricomToken()).thenReturn(completable)
    }


    private fun stubCacheSetExpiryTime(completable: Completable) {
        whenever(cache.setExpireTime(any()))
                .thenReturn(completable)
    }

}