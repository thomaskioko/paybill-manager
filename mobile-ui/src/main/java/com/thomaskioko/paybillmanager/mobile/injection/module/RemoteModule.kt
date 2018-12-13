package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.remote.BuildConfig
import com.thomaskioko.paybillmanager.remote.SafaricomTokenRemoteImpl
import com.thomaskioko.paybillmanager.remote.service.safaricom.SafaricomTokenService
import com.thomaskioko.paybillmanager.remote.service.safaricom.SafaricomTokenServiceFactory
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
        fun provideTokenService(): SafaricomTokenService {
            return SafaricomTokenServiceFactory.makeTokenService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemoteSafaricom: SafaricomTokenRemoteImpl): TokenRemote
}
