package com.thomaskioko.paybillmanager.remote.service.interceptor


import com.thomaskioko.paybillmanager.remote.BuildConfig
import io.reactivex.annotations.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * This class allows us to pass header metadata when making API Requests
 *
 */
class JengaTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + BuildConfig.JENGA_API_KEY)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build()
        return chain.proceed(newRequest)

    }
}
