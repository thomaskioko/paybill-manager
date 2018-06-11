package com.thomaskioko.daraja.api.service

import android.arch.lifecycle.LiveData
import com.thomaskioko.daraja.api.util.ApiResponse
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.model.SafaricomPushRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface SafaricomRequestService {

    @GET("jobs/pending")
    fun getPendingTasks(): LiveData<ApiResponse<SafaricomPushRequest>>

    @POST("mpesa/stkpush/v1/processrequest")
    fun sendPushRequest(@Body safaricomPushRequest: SafaricomPushRequest): LiveData<ApiResponse<PushRequestResponse>>
}
