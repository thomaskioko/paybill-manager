package com.thomaskioko.paybillmanager.data.test.store.billcategory

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import junit.framework.TestCase
import org.junit.Test

class BillCategoryDataStoreFactoryTest {

    private val cacheStore = mock<BillCategoryCacheDataStore>()
    private val factory = BillCategoryDataStoreFactory(cacheStore)


    @Test
    fun getDataStoreReturnsCacheDataStore() {
        TestCase.assertEquals(cacheStore, factory.getCacheDataStore())
    }

}