package com.thomaskioko.paybillmanager.data.store.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaTokenRemoteDataStore @Inject constructor(
        private val tokenRemote: JengaTokenRemote
) : JengaTokenDataStore {

    override fun getJengaToken(): Flowable<JengaTokenEntity> {
        return tokenRemote.getJengaToken()
    }

    override fun clearJengaToken(): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

    override fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

}