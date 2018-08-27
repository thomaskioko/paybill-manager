package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.TokenDataRepository
import com.thomaskioko.paybillmanager.data.mapper.TokenMapper
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import com.thomaskioko.paybillmanager.data.repository.token.TokenDataStore
import com.thomaskioko.paybillmanager.data.store.token.TokenDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.TokenDataFactory
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class TokenDataRepositoryTest {

    private val mapper = mock<TokenMapper>()
    private val factory = mock<TokenDataStoreFactory>()
    private val store = mock<TokenDataStore>()
    private val cache = mock<TokenCache>()
    private val repository = TokenDataRepository(mapper, cache, factory)

    @Before
    fun setup() {
        stubFactoryGetDataStore()
        stubFactoryGetCacheDataStore()
        stubIsCacheExpired(Single.just(false))
        stubIsTokenCached(Single.just(false))
        stubSaveToken(Completable.complete())
    }

    @Test
    fun getTokenCompletes() {
        stubGetToken(Flowable.just(TokenDataFactory.makeSafaricomTokenEntity()))
        stubMapper(TokenDataFactory.makeSafaricomToken(), any())

        val testObserver = repository.getToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val safaricomTokenEntity = TokenDataFactory.makeSafaricomTokenEntity()
        val safaricomToken = TokenDataFactory.makeSafaricomToken()
        stubGetToken(Flowable.just(safaricomTokenEntity))
        stubMapper(safaricomToken, safaricomTokenEntity)

        val testObserver = repository.getToken().test()
        testObserver.assertValue(safaricomToken)
    }

    private fun stubGetToken(observable: Flowable<SafaricomTokenEntity>) {
        whenever(store.getSafaricomToken()).thenReturn(observable)
    }

    private fun stubMapper(model: SafaricomToken, entity: SafaricomTokenEntity) {
        whenever(mapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubIsTokenCached(single: Single<Boolean>) {
        whenever(cache.isTokenCached()).thenReturn(single)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.hasTokenExpired()).thenReturn(single)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }

    private fun stubSaveToken(completable: Completable) {
        whenever(store.saveSafaricomToken(any())).thenReturn(completable)
    }

}