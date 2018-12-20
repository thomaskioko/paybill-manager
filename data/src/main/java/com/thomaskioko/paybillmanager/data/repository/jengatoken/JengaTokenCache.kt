package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface JengaTokenCache {

    fun clearJengaToken(): Completable

    fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable

    fun getJengaToken(): Flowable<JengaTokenEntity>

    fun setExpireTime(expiryTime: Long): Completable

    fun isTokenCached(): Single<Boolean>

    fun hasTokenExpired(): Single<Boolean>
}