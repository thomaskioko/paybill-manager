package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedTokenMapper
import com.thomaskioko.paybillmanager.cache.model.Config
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TokenCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val mapper: CachedTokenMapper
) : TokenCache {
    override fun clearSafaricomToken(): Completable {
        return Completable.defer {
            database.tokenDao().deleteCachedToken()
            Completable.complete()
        }
    }

    override fun saveSafaricomToken(safaricomTokenEntity: SafaricomTokenEntity): Completable {
        return Completable.defer {
            database.tokenDao().insertCachedToken(mapper.mapToCached(safaricomTokenEntity))
            Completable.complete()
        }
    }

    override fun getSafaricomToken(): Flowable<SafaricomTokenEntity> {
        return database.tokenDao().getToken()
                .map { mapper.mapFromCached(it) }
    }

    override fun setExpireTime(lastCache: Long): Completable {
        return Completable.defer {
            database.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isTokenCached(): Single<Boolean> {
        return database.tokenDao().getToken().isEmpty
                .map { !it }
    }

    override fun hasTokenExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()

        return database.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }


}