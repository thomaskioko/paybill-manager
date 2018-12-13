package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface JengaTokenDataStore {

    fun clearJengaToken(): Completable

    fun saveJengaToken(jengaTokenEntity: JengaTokenEntity): Completable

    fun getJengaToken(username: String, password: String): Flowable<JengaTokenEntity>

}