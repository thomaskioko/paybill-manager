package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import javax.inject.Inject


/**
 * Helper class that decides which data source to use.
 */
open class MpesaPushDataStoreFactory @Inject constructor(
        private val cacheDataStore: MpesaPushCacheDataStore,
        private val remoteDataStore: MpesaPushRemoteDataStore
) {

    open fun getDataStore(isCached: Boolean): MpesaPushDataStore {
        return if (!isCached) {
            getRemoteDataStore()
        } else {
            getCacheDataStore()
        }
    }

    open fun getCacheDataStore(): MpesaPushDataStore {
        return cacheDataStore
    }

    open fun getRemoteDataStore(): MpesaPushDataStore {
        return remoteDataStore
    }
}