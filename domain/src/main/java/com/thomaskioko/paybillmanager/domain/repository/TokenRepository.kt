package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import io.reactivex.Completable
import io.reactivex.Observable

interface TokenRepository {

    fun getToken(): Observable<SafaricomToken>

    fun saveToken(safaricomToken: SafaricomToken): Completable
}