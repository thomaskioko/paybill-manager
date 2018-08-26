package com.thomaskioko.daraja.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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
