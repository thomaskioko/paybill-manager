package com.thomaskioko.daraja.di

import com.thomaskioko.daraja.repository.api.service.SafaricomService
import com.thomaskioko.daraja.repository.api.service.SafaricomTokenService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [
    NetworkModule::class,
    RoomModule::class
])

public class SafaricomAppModule {

    @Provides
    @Singleton
    fun provideSafaricomService(@Named("safaricomApi") retrofit: Retrofit): SafaricomService {
        return retrofit.create(SafaricomService::class.java)
    }

    @Provides
    @Singleton
    fun provideSafaricomTokenService(@Named("safaricomTokenApi") retrofit: Retrofit): SafaricomTokenService {
        return retrofit.create(SafaricomTokenService::class.java)
    }
}