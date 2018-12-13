package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedJengaTokenMapper
import com.thomaskioko.paybillmanager.cache.model.Config
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class JengaTokenCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val mapper: CachedJengaTokenMapper
) : JengaTokenCache {

    override fun clearJengaToken(): Completable {
        return Completable.defer {
            database.jengaTokenDao().deleteCachedToken()
            Completable.complete()
        }
    }

    override fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable {
        return Completable.defer {
            database.jengaTokenDao().insertCachedToken(mapper.mapToCached(jengaTokenEntity))
            Completable.complete()
        }
    }

    override fun getJengaToken(): Flowable<JengaTokenEntity> {
        return database.jengaTokenDao().getToken()
                .map { mapper.mapFromCached(it) }
    }

    override fun setExpireTime(expiryTime: Long): Completable {
        return Completable.defer {
            database.configDao().insertConfig(Config(lastCacheTime = expiryTime))
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