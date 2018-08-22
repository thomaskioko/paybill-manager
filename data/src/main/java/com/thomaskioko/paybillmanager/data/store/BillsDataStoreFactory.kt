package com.thomaskioko.paybillmanager.data.store

import com.thomaskioko.paybillmanager.data.repository.BillDataStore
import javax.inject.Inject


/**
 * Helper class that decides which data source to use.
 */
open class BillsDataStoreFactory @Inject constructor(
        private val billCacheDataStore: BillCacheDataStore
) {

    open fun getBillCacheDataStore(): BillDataStore {
        return billCacheDataStore
    }

    open fun getBillRemoteDataStore(){

    }
}