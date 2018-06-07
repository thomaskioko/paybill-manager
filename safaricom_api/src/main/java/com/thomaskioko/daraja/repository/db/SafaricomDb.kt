package com.thomaskioko.daraja.repository.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.thomaskioko.daraja.repository.db.dao.SafaricomPushRequestDao
import com.thomaskioko.daraja.repository.db.dao.SafaricomTokenDao
import com.thomaskioko.daraja.repository.db.entity.SafaricomPushRequest
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken


/**
 * Main database description.
 */
@Database(entities = [(SafaricomToken::class), (SafaricomPushRequest::class)], version = 1)
abstract class SafaricomDb : RoomDatabase() {

    abstract fun safaricomTokenDao(): SafaricomTokenDao

    abstract fun safaricomPushRequestDao(): SafaricomPushRequestDao
}