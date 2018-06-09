package com.thomaskioko.daraja.di

import com.thomaskioko.daraja.api.interceptor.SafaricomAuthInterceptor
import com.thomaskioko.daraja.api.interceptor.SafaricomTokenInterceptor
import com.thomaskioko.daraja.api.util.livedata.LiveDataCallAdapterFactory
import com.thomaskioko.daraja.util.AppConstants.Companion.CONNECT_TIMEOUT
import com.thomaskioko.daraja.util.AppConstants.Companion.READ_TIMEOUT
import com.thomaskioko.daraja.util.AppConstants.Companion.SAFARICOM_BASE_URL
import com.thomaskioko.daraja.util.AppConstants.Companion.TOKEN_URL
import com.thomaskioko.daraja.util.AppConstants.Companion.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton


@Module
public class NetworkModule {

    @Provides
    @Singleton
    @Named("OkHttpSafaricomTokenClient")
    fun okHttpSafaricomTokenClient(@Named("paymentLogger") httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(SafaricomTokenInterceptor())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    @Singleton
    @Named("safaricomTokenApi")
    fun provideSafaricomTokenRetrofitClient(@Named("OkHttpSafaricomTokenClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TOKEN_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
                .build()
    }

    @Provides
    @Singleton
    fun provideSafaricomAuthInterceptor(): SafaricomAuthInterceptor { // This is where the Interceptor object is constructed
        return SafaricomAuthInterceptor
    }

    @Provides
    @Singleton
    @Named("OkHttpSafaricomClient")
    fun okHttpSafaricomClient(@Named("paymentLogger") httpLoggingInterceptor: HttpLoggingInterceptor,
                              safaricomAuthInterceptor: SafaricomAuthInterceptor): OkHttpClient {

        return OkHttpClient.Builder()
                .addInterceptor(safaricomAuthInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(CONNECT_TIMEOUT.toLong(), TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }


    @Provides
    @Singleton
    @Named("safaricomApi")
    fun provideSafaricomRetrofitClient(@Named("OkHttpSafaricomClient") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(SAFARICOM_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create()) // Serialize Objects
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //Set call to return {@link Observable}
                .build()
    }

}