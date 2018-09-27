package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.remote.BuildConfig
import com.thomaskioko.paybillmanager.remote.TokenRemoteImpl
import com.thomaskioko.paybillmanager.remote.service.TokenService
import com.thomaskioko.paybillmanager.remote.service.TokenServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideTokenService(): TokenService {
            return TokenServiceFactory.makeTokenService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: TokenRemoteImpl): TokenRemote
}