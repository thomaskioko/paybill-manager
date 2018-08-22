package com.thomaskioko.paybillmanager.data.store

import com.thomaskioko.paybillmanager.data.repository.BillDataStore
import javax.inject.Inject


/**
 * Helper class that decides which data source to use.
 */
open class BillsDataStoreFactory @Inject constructor(
        private val billsCacheDataStore: BillsCacheDataStore
) {

    open fun getCacheDataStore(): BillDataStore {
        return billsCacheDataStore
    }

    open fun getRemoteDataStore(){

    }
}