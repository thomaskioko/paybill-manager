package com.thomaskioko.daraja.di

import com.thomaskioko.daraja.api.service.SafaricomRequestService
import com.thomaskioko.daraja.api.service.SafaricomTokenService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [
    NetworkModule::class,
    DarajaRoomModule::class
])

public class SafaricomAppModule {

    @Provides
    @Singleton
    fun provideSafaricomService(@Named("safaricomApi") retrofit: Retrofit): SafaricomRequestService {
        return retrofit.create(SafaricomRequestService::class.java)
    }

    @Provides
    @Singleton
    fun provideSafaricomTokenService(@Named("safaricomTokenApi") retrofit: Retrofit): SafaricomTokenService {
        return retrofit.create(SafaricomTokenService::class.java)
    }
}
