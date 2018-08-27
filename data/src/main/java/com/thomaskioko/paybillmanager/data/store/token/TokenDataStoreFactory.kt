package com.thomaskioko.paybillmanager.data.store.token

import com.thomaskioko.paybillmanager.data.repository.token.TokenDataStore
import javax.inject.Inject

open class TokenDataStoreFactory @Inject constructor(
        private val tokenCacheDataStore: TokenCacheDataStore,
        private val tokenRemoteDataStore: TokenRemoteDataStore
) {
    open fun getDataStore(tokenCached: Boolean, tokenExpired: Boolean): TokenDataStore {
        return if (tokenCached && !tokenExpired) {
            tokenCacheDataStore
        } else {
            tokenRemoteDataStore
        }
    }

    open fun getCacheDataStore(): TokenDataStore {
        return tokenCacheDataStore
    }

    open fun getRemoteDataStore(): TokenRemoteDataStore {
        return tokenRemoteDataStore
    }
}