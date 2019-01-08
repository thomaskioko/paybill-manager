package com.thomaskioko.paybillmanager.data.test.store.bills

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.bills.BillsCacheDataStore
import com.thomaskioko.paybillmanager.data.store.bills.BillsDataStoreFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BillsDataStoreFactoryTest {

    private val cacheStore = mock<BillsCacheDataStore>()
    private val factory = BillsDataStoreFactory(cacheStore)


    @Test
    fun getDataStoreReturnsCacheDataStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}