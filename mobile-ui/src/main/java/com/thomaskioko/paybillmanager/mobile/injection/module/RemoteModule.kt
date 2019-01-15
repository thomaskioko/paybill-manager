package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaRemote
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRemote
import com.thomaskioko.paybillmanager.remote.BuildConfig
import com.thomaskioko.paybillmanager.remote.JengaRemoteImpl
import com.thomaskioko.paybillmanager.remote.MpesaRemoteImpl
import com.thomaskioko.paybillmanager.remote.service.JengaService
import com.thomaskioko.paybillmanager.remote.service.JengaServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module
@Suppress("unused")
abstract class RemoteModule {

    /**
     * This companion object annotated as a module is necessary in order to provide dependencies
     * statically in case the wrapping module is an abstract class (to use binding)
     */
    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideJengaService(): JengaService {
            return JengaServiceFactory.makeJengaService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindJengaRemote(jengaRemoteImpl: JengaRemoteImpl): JengaRemote

    @Binds
    abstract fun bindMpesaPushRemote(mpesaRemoteImpl: MpesaRemoteImpl): MpesaPushRemote
}
