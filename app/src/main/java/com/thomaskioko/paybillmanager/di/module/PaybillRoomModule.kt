package com.thomaskioko.paybillmanager.di.module

import android.app.Application
import androidx.room.Room
import com.thomaskioko.paybillmanager.db.PaybillDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PaybillRoomModule {

    @Singleton
    @Provides
    fun providePaybillDb(app: Application): PaybillDatabase {
        return Room
                .databaseBuilder(app, PaybillDatabase::class.java, "paybill_db")
                .fallbackToDestructiveMigration()
                .build()
    }
}