package com.thomaskioko.paybillmanager.data.repository.token

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface TokenCache {

    fun clearSafaricomToken(): Completable

    fun saveSafaricomToken(safaricomTokenEntity: SafaricomTokenEntity): Completable

    fun getSafaricomToken(): Flowable<SafaricomTokenEntity>

    fun setExpireTime(lastCache: Long): Completable

    fun isTokenCached(): Single<Boolean>

    fun hasTokenExpired(): Single<Boolean>
}