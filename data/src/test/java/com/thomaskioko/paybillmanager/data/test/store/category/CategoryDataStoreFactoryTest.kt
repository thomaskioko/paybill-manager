package com.thomaskioko.paybillmanager.data.test.store.category

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.store.category.CategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.store.category.CategoryDataStoreFactory
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryDataStoreFactoryTest {

    private val cacheStore = mock<CategoryCacheDataStore>()
    private val factory = CategoryDataStoreFactory(cacheStore)


    @Test
    fun getDataStoreReturnsCacheDataStore() {
        assertEquals(cacheStore, factory.getCacheDataStore())
    }
}