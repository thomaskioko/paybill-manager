package com.thomaskioko.paybillmanager.data.store.category

import com.thomaskioko.paybillmanager.data.repository.category.CategoryDataStore
import javax.inject.Inject


/**
 * Helper class that decides which data source to use.
 */
open class CategoryDataStoreFactory @Inject constructor(
        private val categoryCacheDataStore: CategoryCacheDataStore
) {

    open fun getCacheDataStore(): CategoryDataStore {
        return categoryCacheDataStore
    }

    open fun getRemoteDataStore(){

    }
}