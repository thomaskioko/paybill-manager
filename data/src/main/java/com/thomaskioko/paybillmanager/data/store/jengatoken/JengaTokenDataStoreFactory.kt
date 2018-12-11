package com.thomaskioko.paybillmanager.data.store.jengatoken

import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import javax.inject.Inject

open class JengaTokenDataStoreFactory @Inject constructor(
        private val tokenCacheDataStore: JengaTokenCacheDataStore,
        private val tokenRemoteDataStore: JengaTokenRemoteDataStore
) {
    open fun getDataStore(tokenCached: Boolean, tokenExpired: Boolean): JengaTokenDataStore {
        return if (tokenCached && !tokenExpired) {
            tokenCacheDataStore
        } else {
            tokenRemoteDataStore
        }
    }

    open fun getCacheDataStore(): JengaTokenDataStore {
        return tokenCacheDataStore
    }

    open fun getRemoteDataStore(): JengaTokenRemoteDataStore {
        return tokenRemoteDataStore
    }
}