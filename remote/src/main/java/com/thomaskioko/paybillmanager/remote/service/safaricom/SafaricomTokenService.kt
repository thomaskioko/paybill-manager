package com.thomaskioko.paybillmanager.remote.service.safaricom


import com.thomaskioko.paybillmanager.remote.model.SafaricomTokenModel
import io.reactivex.Flowable
import retrofit2.http.GET

interface SafaricomTokenService {

    @GET("generate?grant_type=client_credentials")
    fun getAccessToken(): Flowable<SafaricomTokenModel>
}
