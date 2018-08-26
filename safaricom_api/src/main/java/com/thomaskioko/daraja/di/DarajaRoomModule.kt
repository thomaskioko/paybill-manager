package com.thomaskioko.daraja.di

import android.app.Application
import androidx.room.Room
import com.thomaskioko.daraja.db.SafaricomDb
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DarajaRoomModule {


    @Singleton
    @Provides
    fun provideRoomDb(app: Application): SafaricomDb {
        return Room
                .databaseBuilder(app, SafaricomDb::class.java, "safaricom_db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideSafaricomTokenDao(db: SafaricomDb): SafaricomTokenDao {
        return db.safaricomTokenDao()
    }


    @Singleton
    @Provides
    fun provideSafaricomPushRequestDao(db: SafaricomDb): SafaricomPushRequestDao {
        return db.safaricomPushRequestDao()
    }
}
