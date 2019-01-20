package com.thomaskioko.paybillmanager.data.store.jengatoken

import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import javax.inject.Inject

open class JengaTokenDataStoreFactory @Inject constructor(
        private val tokenCacheDataStore: JengaTokenCacheDataStore,
        private val tokenRemoteDataStore: JengaTokenRemoteDataStore
) {
    open fun getDataStore(tokenExpired: Boolean): JengaTokenDataStore {
        return if (!tokenExpired) {
            getCacheDataStore()
        } else {
            getRemoteDataStore()
        }
    }

    open fun getCacheDataStore(): JengaTokenDataStore {
        return tokenCacheDataStore
    }

    open fun getRemoteDataStore(): JengaTokenDataStore {
        return tokenRemoteDataStore
    }
}