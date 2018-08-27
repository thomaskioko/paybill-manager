package com.thomaskioko.paybillmanager.data.test.store.token

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.token.TokenCacheDataStore
import com.thomaskioko.paybillmanager.data.store.token.TokenDataStoreFactory
import com.thomaskioko.paybillmanager.data.store.token.TokenRemoteDataStore
import junit.framework.TestCase.assertEquals
import org.junit.Test

class TokenDataStoreFactoryTest {

    private val cacheStore = mock<TokenCacheDataStore>()
    private val remoteStore = mock<TokenRemoteDataStore>()
    private val factory = TokenDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getDataStoreReturnsRemoteStoreWhenCacheExpired() {
        assertEquals(remoteStore, factory.getDataStore(true, true))
    }

    @Test
    fun getDataStoreReturnsRemoteStoreWhenProjectsNotCached() {
        assertEquals(remoteStore, factory.getDataStore(false, false))
    }

    @Test
    fun getDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getDataStore(true, false))
    }

    @Test
    fun getCacheDataStoreReturnsCacheStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}