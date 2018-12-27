package com.thomaskioko.paybillmanager.data.test.store.billcategory

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryDataStore
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import junit.framework.TestCase
import org.junit.Test

class BillCategoryDataStoreFactoryTest {

    private val cacheStore = mock<BillCategoryDataStore>()
    private val factory = BillCategoryDataStoreFactory(cacheStore)


    @Test
    fun getDataStoreReturnsCacheDataStore() {
        TestCase.assertEquals(cacheStore, factory.getCacheDataStore())
    }

}