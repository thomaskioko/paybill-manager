package com.thomaskioko.paybillmanager.di.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.thomaskioko.daraja.di.NetworkModule
import com.thomaskioko.daraja.di.DarajaRoomModule
import com.thomaskioko.paybillmanager.R
import dagger.Module
import dagger.Provides
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton


@Module(
        includes = [
            NetworkModule::class,
            DarajaRoomModule::class
        ])
@Suppress("unused")
class AppModule {

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/WorkSans-Regular.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }

    @Provides
    @Singleton
    fun provideSharePreference(app: Application): SharedPreferences {
        return app.getSharedPreferences("paybill_manager", Context.MODE_PRIVATE)
    }
}
