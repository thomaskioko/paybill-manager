package com.thomaskioko.daraja.api.interceptor

import android.support.annotation.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Singleton


/**
 * This class add information (API Key) to [okhttp3.OkHttpClient] which is passed in
 * [NetworkModule.okHttpSafaricomTokenClient]
 * which is required when making a request. This will ensure that all requests are made with the API key
 *
 * @author Thomas Kioko
 */

/**
 * Default constructor.
 */
@Singleton
object SafaricomAuthInterceptor : Interceptor {

    private var authToken: String? = null

    fun setAuthToken(authToken: String) {
        this.authToken = authToken
    }

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Bearer " + authToken!!)
                .build()
        return chain.proceed(newRequest)

    }
}