package com.thomaskioko.paybillmanager.remote.service.interceptor


import android.util.Base64
import com.thomaskioko.paybillmanager.remote.BuildConfig
import io.reactivex.annotations.NonNull
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


/**
 * This class add information (API Key) to {@link okhttp3.OkHttpClient} which is passed in
 * {@link PaymentNetworkModule#okHttpSafaricomTokenClient(HttpLoggingInterceptor)}
 * which is required when generating an accessToken.
 *
 */
class TokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response {

        val keys = BuildConfig.SAFARICOM_CONSUMER_KEY + ":" + BuildConfig.SAFARICOM_CONSUMER_SECRET
        val newRequest = chain.request().newBuilder()
                .addHeader("Authorization", "Basic " + Base64.encodeToString(keys.toByteArray(), Base64.NO_WRAP))
                .addHeader("cache-control", "no-cache")
                .build()
        return chain.proceed(newRequest)

    }
}
