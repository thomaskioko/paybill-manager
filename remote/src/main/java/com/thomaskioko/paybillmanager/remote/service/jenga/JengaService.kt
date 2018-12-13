package com.thomaskioko.paybillmanager.remote.service.jenga

import com.thomaskioko.paybillmanager.remote.model.JengaToken
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface JengaService {

    @FormUrlEncoded
    @GET("identity/v2/token")
    fun getAccessToken(
            @Field("username") username: String,
            @Field("password") password: String
    ): Flowable<JengaToken>

}