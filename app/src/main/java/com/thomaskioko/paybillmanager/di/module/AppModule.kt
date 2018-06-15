package com.thomaskioko.paybillmanager.di.module

import com.thomaskioko.daraja.di.NetworkModule
import com.thomaskioko.daraja.di.RoomModule
import com.thomaskioko.paybillmanager.R
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import uk.co.chrisjenx.calligraphy.CalligraphyTypefaceSpan
import uk.co.chrisjenx.calligraphy.TypefaceUtils
import javax.inject.Singleton


@Module(
        includes = [
            NetworkModule::class,
            RoomModule::class
        ])
class AppModule {

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/WorkSans-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }
}
