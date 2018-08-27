package com.thomaskioko.paybillmanager.data.repository.token

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface TokenDataStore {

    fun clearSafaricomToken(): Completable

    fun saveSafaricomToken(safaricomTokenEntity: SafaricomTokenEntity): Completable

    fun getSafaricomToken(): Flowable<SafaricomTokenEntity>

}