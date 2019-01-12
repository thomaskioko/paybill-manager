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

    /**
     * Returns a DataStore based on whether or not there is content in the cache
     */
    open fun getDataStore(isCached: Boolean): MpesaPushDataStore {
        if (isCached) {
            return retrieveCacheDataStore()
        }
        return retrieveRemoteDataStore()
    }

    /**
     * Return an instance of the Cache Data Store
     */
    open fun retrieveCacheDataStore(): MpesaPushDataStore {
        return cacheDataStore
    }

    /**
     * Return an instance of the Remote Data Store
     */
    open fun retrieveRemoteDataStore(): MpesaPushDataStore {
        return remoteDataStore
    }
}