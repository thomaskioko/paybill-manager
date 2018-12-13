package com.thomaskioko.paybillmanager.data.store.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaRemote
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaTokenRemoteDataStore @Inject constructor(
        private val remote: JengaRemote
) : JengaTokenDataStore {
    override fun clearJengaToken(): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

    override fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

    override fun getJengaToken(username: String, password: String): Flowable<JengaTokenEntity> {
        return remote.getJengaToken(username, password)
    }

}