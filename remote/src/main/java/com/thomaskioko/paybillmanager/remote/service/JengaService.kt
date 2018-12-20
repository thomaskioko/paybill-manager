package com.thomaskioko.paybillmanager.remote.service

import com.thomaskioko.paybillmanager.remote.model.JengaToken
import io.reactivex.Flowable
import retrofit2.http.*

interface JengaService {

    @FormUrlEncoded
    @POST("identity/v2/token")
    fun getAccessToken(
            @Header("Authorization") bearer: String,
            @Header("Content-Type") contentType: String,
            @Field("username") username: String,
            @Field("password") password: String
    ): Flowable<JengaToken>

    @GET("transaction/v2/billers?per_page=2&page=10")
    fun getAllBillers(
            @Header("Authorization") bearer: String,
            @Header("Content-Type") contentType: String)

    @POST("transaction/v2/payment/mpesastkpush")
    fun makeMpesaPayment(
            @Header("Authorization") bearer: String,
            @Header("Content-Type") contentType: String,
            @Header("signature") signature: String
    )

}