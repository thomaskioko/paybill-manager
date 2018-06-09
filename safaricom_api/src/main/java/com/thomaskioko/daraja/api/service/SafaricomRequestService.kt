package com.thomaskioko.daraja.api.service

import android.arch.lifecycle.LiveData
import com.thomaskioko.daraja.model.SafaricomPushRequest
import com.thomaskioko.daraja.api.util.ApiResponse
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface SafaricomService {

    @get:GET("jobs/pending")
    val tasks: LiveData<ApiResponse<SafaricomPushRequest>>

    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPushRequest(@Body safaricomPushRequest: SafaricomPushRequest): LiveData<ApiResponse<PushRequestResponse>>
}