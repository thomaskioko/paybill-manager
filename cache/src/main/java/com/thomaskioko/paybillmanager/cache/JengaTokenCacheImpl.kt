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

    override fun isTokenCached(): Single<Boolean> {
        return database.jengaTokenDao().getToken().isEmpty
                .map { !it }
    }

    override fun setExpireTime(expiryTime: Long): Completable {
        val tokenExpireMilliseconds = System.currentTimeMillis() + (expiryTime * 1000)
        return Completable.defer {
            database.configDao().insertConfig(Config(lastCacheTime = tokenExpireMilliseconds))
            Completable.complete()
        }
    }

    override fun hasTokenExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        return database.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime > it.lastCacheTime
                }
    }


}