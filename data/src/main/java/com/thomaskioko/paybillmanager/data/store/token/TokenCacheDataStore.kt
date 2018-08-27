package com.thomaskioko.paybillmanager.data.store.token

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import com.thomaskioko.paybillmanager.data.repository.token.TokenDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class TokenCacheDataStore @Inject constructor(
        private val tokenCache: TokenCache
) : TokenDataStore {

    override fun clearSafaricomToken(): Completable {
        return tokenCache.clearSafaricomToken()
    }

    override fun saveSafaricomToken(safaricomTokenEntity: SafaricomTokenEntity): Completable {
        return tokenCache.saveSafaricomToken(safaricomTokenEntity)
                .andThen(tokenCache.setExpireTime(safaricomTokenEntity.expiresIn))
    }

    override fun getSafaricomToken(): Flowable<SafaricomTokenEntity> {
        return tokenCache.getSafaricomToken()
    }

}