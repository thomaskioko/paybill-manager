package com.thomaskioko.paybillmanager.data.test.store

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.BillsCacheDataStore
import com.thomaskioko.paybillmanager.data.store.BillsDataStoreFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BillsDataStoreFactoryTest {

    private val cacheStore = mock<BillsCacheDataStore>()
    private val factory = BillsDataStoreFactory(cacheStore)


    @Test
    fun getDataStoreReturnsCacheDataStore(){
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}