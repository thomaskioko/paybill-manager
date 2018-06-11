package com.thomaskioko.daraja.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.thomaskioko.daraja.db.converter.DateTimeTypeConverters
import com.thomaskioko.daraja.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.db.entity.SafaricomToken


/**
 * Main database description.
 */
@Database(
        entities = [
            SafaricomToken::class,
            PushRequestResponse::class
        ],
        version = 1,
        exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class SafaricomDb : RoomDatabase() {

    abstract fun safaricomTokenDao(): SafaricomTokenDao

    abstract fun safaricomPushRequestDao(): SafaricomPushRequestDao
}
