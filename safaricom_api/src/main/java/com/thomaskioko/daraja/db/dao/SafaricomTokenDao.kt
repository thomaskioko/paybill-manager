package com.thomaskioko.daraja.db.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.daraja.db.entity.SafaricomToken


/**
 * Interface for database access on Token related operations.
 */
@Dao
abstract class SafaricomTokenDao {

    @Query("SELECT * FROM SafaricomToken")
    abstract fun loadAccessToken(): LiveData<SafaricomToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(safaricomToken: SafaricomToken)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun update(safaricomToken: SafaricomToken) {
        deleteAll()
        insert(safaricomToken)
    }

    @Query("DELETE FROM SafaricomToken")
    abstract fun deleteAll()
}
