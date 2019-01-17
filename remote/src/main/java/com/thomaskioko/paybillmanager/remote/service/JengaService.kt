package com.thomaskioko.paybillmanager.remote.service

import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.remote.model.JengaToken
import com.thomaskioko.paybillmanager.remote.model.MpesaPushResponse
import io.reactivex.Flowable
import retrofit2.http.*

interface JengaService {

    @FormUrlEncoded
    @POST("identity/v2/token")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    fun getAccessToken(
            @Header("Authorization") bearer: String,
            @Field("username") username: String,
            @Field("password") password: String
    ): Flowable<JengaToken>

    @GET("transaction/v2/billers?per_page=2&page=10")
    fun getAllBillers(
            @Header("Authorization") bearer: String,
            @Header("Content-Type") contentType: String)


    @POST("transaction/v2/payment/mpesastkpush")
    @Headers("Content-Type: application/json")
    fun getMpesaStkPush(
            @Header("Authorization") bearer: String,
            @Header("signature") signature: String,
            @Body mpesaPushRequest: MpesaPushRequest
    ): Flowable<MpesaPushResponse>
}
