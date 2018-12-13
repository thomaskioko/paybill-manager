package com.thomaskioko.paybillmanager.remote.service.safaricom

import com.google.gson.Gson
import com.thomaskioko.paybillmanager.remote.service.interceptor.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object SafaricomTokenServiceFactory {

    open fun makeTokenService(isDebug: Boolean): SafaricomTokenService {
        val okHttpClient = makeOkHttpClient(
                makeLoggingInterceptor((isDebug)))
        return makeTokenTrendingService(okHttpClient, Gson())
    }

    private fun makeOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(TokenInterceptor())
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    private fun makeTokenTrendingService(okHttpClient: OkHttpClient, gson: Gson): SafaricomTokenService {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://sandbox.safaricom.co.ke/oauth/v1/")
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        return retrofit.create(SafaricomTokenService::class.java)
    }

}