package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.MpesaPushDataRepository
import com.thomaskioko.paybillmanager.data.mapper.MpesaPushRequestMapper
import com.thomaskioko.paybillmanager.data.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushCacheDataStore
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushDataStoreFactory
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushRemoteDataStore
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushDataRepositoryTest {

    private lateinit var dataRepository: MpesaPushDataRepository

    private lateinit var dataStoreFactory: MpesaPushDataStoreFactory
    private lateinit var requestMapper: MpesaPushRequestMapper
    private lateinit var responseMapper: MpesaPushResponseMapper
    private lateinit var cacheDataStore: MpesaPushCacheDataStore
    private lateinit var remoteDataStore: MpesaPushRemoteDataStore
    private lateinit var dataStore: MpesaPushDataStore

    @Before
    fun setUp() {
        dataStoreFactory = mock()
        requestMapper = mock()
        responseMapper = mock()
        cacheDataStore = mock()
        remoteDataStore = mock()
        dataStore = mock()
        dataRepository = MpesaPushDataRepository(dataStoreFactory, requestMapper, responseMapper)
        stubDataStoreFactoryGetCacheDataStore()
        stubDataStoreFactoryGetRemoteDataStore()
    }

    @Test
    fun saveMpesaPushResponseCompletes() {
        stubCacheSaveMpesaPushResponse(Completable.complete())

        val testObserver = dataStore.saveMpesaPushResponse(
                DataFactory.makeMpesaPushResponseEntity()).test()
        testObserver.assertComplete()
    }

    private fun stubCacheSaveMpesaPushResponse(completable: Completable) {
        whenever(dataStore.saveMpesaPushResponse(any()))
                .thenReturn(completable)
    }

    private fun stubDataStoreFactoryGetCacheDataStore() {
        whenever(dataStoreFactory.retrieveCacheDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubDataStoreFactoryGetRemoteDataStore() {
        whenever(dataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(cacheDataStore)
    }


}