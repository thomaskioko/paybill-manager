package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenRemote
import com.thomaskioko.paybillmanager.remote.BuildConfig
import com.thomaskioko.paybillmanager.remote.JengaTokenRemoteImpl
import com.thomaskioko.paybillmanager.remote.service.JengaService
import com.thomaskioko.paybillmanager.remote.service.JengaServiceFactory
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
        fun provideJengaService(): JengaService {
            return JengaServiceFactory.makeJengaService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindJengaRemote(jengaRemoteImpl: JengaTokenRemoteImpl): JengaTokenRemote
}
