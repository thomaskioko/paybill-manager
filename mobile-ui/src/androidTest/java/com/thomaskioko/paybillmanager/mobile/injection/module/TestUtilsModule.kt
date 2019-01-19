package com.thomaskioko.paybillmanager.mobile.injection.module

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.mobile.util.FileUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Suppress("unused")
@Module
class TestUtilsModule {

    @Provides
    @Singleton
    fun provideDeviceUtil(application: Application): FileUtils {
        return mock()
    }

}