package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.JengaToken
import io.reactivex.Observable

interface JengaTokenRepository {

    fun getJengaToken(): Observable<JengaToken>
}