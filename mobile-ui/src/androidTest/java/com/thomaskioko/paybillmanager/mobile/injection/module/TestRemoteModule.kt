package com.thomaskioko.paybillmanager.mobile.injection.module

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.remote.service.TokenService
import dagger.Module
import dagger.Provides

@Module
object TestRemoteModule {


    @Provides
    @JvmStatic
    fun provideTokenService(): TokenService {
        return mock()
    }

    @Provides
    @JvmStatic
    fun provideTokenRemote(): TokenRemote {
        return mock()
    }
}