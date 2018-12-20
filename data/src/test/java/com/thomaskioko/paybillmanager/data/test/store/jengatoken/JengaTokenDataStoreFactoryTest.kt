package com.thomaskioko.paybillmanager.data.test.store.jengatoken

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenCacheDataStore
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenDataStoreFactory
import com.thomaskioko.paybillmanager.data.store.jengatoken.JengaTokenRemoteDataStore
import junit.framework.TestCase.assertEquals
import org.junit.Test

class JengaTokenDataStoreFactoryTest {

    private val cacheStore = mock<JengaTokenCacheDataStore>()
    private val remoteStore = mock<JengaTokenRemoteDataStore>()
    private val factory = JengaTokenDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}