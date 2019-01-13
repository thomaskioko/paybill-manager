package com.thomaskioko.paybillmanager.data.test.store.mpesapush

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushCache
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushCacheDataStore
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushDataStoreFactory
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushRemoteDataStore
import io.reactivex.Single
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushDataStoreFactoryTest {

    private lateinit var dataStoreFactory: MpesaPushDataStoreFactory

    private lateinit var cache: MpesaPushCache
    private lateinit var cacheDataStore: MpesaPushCacheDataStore
    private lateinit var remoteDataStore: MpesaPushRemoteDataStore

    @Before
    fun setUp() {
        cache = mock()
        cacheDataStore = mock()
        remoteDataStore = mock()
        dataStoreFactory = MpesaPushDataStoreFactory(cacheDataStore, remoteDataStore)
    }

    @Test
    fun retrieveDataStoreWhenNotCachedReturnsRemoteDataStore() {
        stubIsStkResponseCached(Single.just(false))
        val dataStore = dataStoreFactory.retrieveDataStore(false)

        assertEquals(dataStore, remoteDataStore)
    }

    @Test
    fun retrieveDataStoreReturnsCacheDataStore() {
        stubIsStkResponseCached(Single.just(true))
        val dataStore = dataStoreFactory.retrieveDataStore(true)

        assertEquals(dataStore, cacheDataStore)
    }

    @Test
    fun retrieveCacheDataStoreReturnsCacheDataStore() {
        val dataStore = dataStoreFactory.retrieveCacheDataStore()
        assert(dataStore is MpesaPushCacheDataStore)
    }

    @Test
    fun retrieveRemoteDataStoreReturnsCacheDataStore() {
        val dataStore = dataStoreFactory.retrieveRemoteDataStore()
        assert(dataStore is MpesaPushRemoteDataStore)
    }


    private fun stubIsStkResponseCached(isCached: Single<Boolean>) {
        whenever(cache.isStkResponseCached(any()))
                .thenReturn(isCached)

    }
}