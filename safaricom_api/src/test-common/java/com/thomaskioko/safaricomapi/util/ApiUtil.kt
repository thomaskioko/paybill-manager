package com.thomaskioko.safaricomapi.util

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.thomaskioko.daraja.repository.api.util.ApiResponse
import retrofit2.Response

object ApiUtil {
    fun <T : Any> successCall(data: T) = createCall(Response.success(data))

    fun <T : Any> createCall(response: Response<T>) = MutableLiveData<ApiResponse<T>>().apply {
        value = ApiResponse.create(response)
    } as LiveData<ApiResponse<T>>
}
