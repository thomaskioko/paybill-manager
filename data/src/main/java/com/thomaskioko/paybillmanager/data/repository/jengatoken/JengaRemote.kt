package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import io.reactivex.Flowable

interface JengaRemote {

    fun getJengaToken(username: String, password: String): Flowable<JengaTokenEntity>
}