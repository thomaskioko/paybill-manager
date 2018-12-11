package com.thomaskioko.paybillmanager.data.repository.jengatoken

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import io.reactivex.Flowable

interface JengaTokenRemote {

    fun getJengaToken(): Flowable<JengaTokenEntity>
}