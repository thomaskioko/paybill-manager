package com.thomaskioko.daraja.di

import android.app.Application
import android.arch.persistence.room.Room
import com.thomaskioko.daraja.db.SafaricomDb
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
public class RoomModule constructor(
        mApplication: Application
) {

    var demoDatabase = Room.databaseBuilder(mApplication, SafaricomDb::class.java, "safaricom_db").build()

    @Singleton
    @Provides
    fun providesRoomDatabase(): SafaricomDb {
        return demoDatabase
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