package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.JengaTokenDataRepository
import com.thomaskioko.paybillmanager.data.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
class JengaTokenDataRepositoryTest {

    private val mapper = mock<JengaTokenMapper>()
    private val factory = mock<JengaTokenDataStoreFactory>()
    private val store = mock<JengaTokenDataStore>()
    private val cache = mock<JengaTokenCache>()
    private val repository = JengaTokenDataRepository(mapper, cache, factory)

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
        stubGetToken(Flowable.just(DataFactory.makeJengaTokenEntity()))
        stubMapper(DataFactory.makeJengaToken(), any())

        val testObserver = repository.getJengaToken().test()
        testObserver.assertComplete()
    }

    @Test
    fun getTokenReturnsData() {
        val jengaTokenEntity = DataFactory.makeJengaTokenEntity()
        val jengaToken = DataFactory.makeJengaToken()
        stubGetToken(Flowable.just(jengaTokenEntity))
        stubMapper(jengaToken, jengaTokenEntity)

        val testObserver = repository.getJengaToken().test()
        testObserver.assertValue(jengaToken)
    }

    private fun stubGetToken(observable: Flowable<JengaTokenEntity>) {
        whenever(store.getJengaToken()).thenReturn(observable)
    }

    private fun stubMapper(model: JengaToken, entity: JengaTokenEntity) {
        whenever(mapper.mapFromEntity(entity)).thenReturn(model)
    }

    private fun stubIsTokenCached(single: Single<Boolean>) {
        whenever(cache.isTokenCached()).thenReturn(single)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.hasTokenExpired()).thenReturn(single)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any())).thenReturn(store)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }

    private fun stubSaveToken(completable: Completable) {
        whenever(store.saveJengaToken(any())).thenReturn(completable)
    }

}