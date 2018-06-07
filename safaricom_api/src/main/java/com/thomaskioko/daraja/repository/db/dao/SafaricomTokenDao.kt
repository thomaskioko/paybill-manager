package com.thomaskioko.daraja.repository.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken


/**
 * Interface for database access on Token related operations.
 */
@Dao
abstract class SafaricomTokenDao {

    @Query("SELECT * FROM SafaricomToken Limit 1")
    abstract fun getAccessToken(): LiveData<SafaricomToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg videos: SafaricomToken)

    @Query("DELETE FROM SafaricomToken")
    abstract fun deleteAll()
}