package com.thomaskioko.paybillmanager.di.module

import com.thomaskioko.daraja.di.NetworkModule
import com.thomaskioko.daraja.di.RoomModule
import dagger.Module


@Module(
        includes = [
            NetworkModule::class,
            RoomModule::class
        ])
class AppModule
