package com.thomaskioko.paybillmanager.remote.service


import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.remote.model.SafaricomTokenModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface TokenService {

    @GET("generate?grant_type=client_credentials")
    fun getAccessToken(): Flowable<SafaricomTokenModel>
}
