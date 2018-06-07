package com.thomaskioko.daraja.repository.api.service

import android.arch.lifecycle.LiveData
import com.thomaskioko.daraja.repository.api.util.ApiResponse
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken
import retrofit2.http.GET


interface SafaricomTokenService {

    @GET("generate?grant_type=client_credentials")
    fun getAccessToken(): LiveData<ApiResponse<SafaricomToken>>
}
