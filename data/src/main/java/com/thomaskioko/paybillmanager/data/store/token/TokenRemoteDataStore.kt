package com.thomaskioko.paybillmanager.data.store.token

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenDataStore
import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class TokenRemoteDataStore @Inject constructor(
        private val tokenRemote: TokenRemote
) : TokenDataStore {

    override fun getSafaricomToken(): Flowable<SafaricomTokenEntity> {
        return tokenRemote.getSafaricomToken()
    }

    override fun clearSafaricomToken(): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

    override fun saveSafaricomToken(safaricomTokenEntity: SafaricomTokenEntity): Completable {
        throw UnsupportedOperationException("Saving token isn't supported here...")
    }

}