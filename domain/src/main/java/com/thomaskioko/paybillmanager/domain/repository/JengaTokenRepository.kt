package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.JengaToken
import io.reactivex.Flowable

interface JengaTokenRepository {

    fun getJengaToken(): Flowable<JengaToken>
}