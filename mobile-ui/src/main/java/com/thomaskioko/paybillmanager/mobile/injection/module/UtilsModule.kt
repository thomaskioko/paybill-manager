package com.thomaskioko.paybillmanager.mobile.injection.module

import android.app.Application
import com.thomaskioko.paybillmanager.mobile.util.FileUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class UtilsModule {

    @Provides
    @Singleton
    fun provideDeviceUtil(application: Application): FileUtils {
        return FileUtils(application)
    }

}