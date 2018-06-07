package com.thomaskioko.daraja.repository.api.service

import android.arch.lifecycle.LiveData
import com.thomaskioko.daraja.repository.api.util.ApiResponse
import com.thomaskioko.daraja.repository.db.entity.SafaricomPushRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface SafaricomService {

    @get:GET("jobs/pending")
    val tasks: LiveData<ApiResponse<SafaricomPushRequest>>

    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPush(@Body safaricomPushRequest: SafaricomPushRequest): LiveData<ApiResponse<SafaricomPushRequest>>
}