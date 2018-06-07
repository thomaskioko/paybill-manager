package com.thomaskioko.daraja.repository.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.database.sqlite.SQLiteConstraintException
import com.thomaskioko.daraja.repository.db.entity.SafaricomToken


/**
 * Interface for database access on Token related operations.
 */
@Dao
abstract class SafaricomTokenDao {

    @Query("SELECT * FROM SafaricomToken Limit 1")
    abstract fun getAccessToken(): LiveData<SafaricomToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(safaricomToken: SafaricomToken)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun insertSafaricomToken(safaricomToken: SafaricomToken) {
        deleteAll()
        insert(safaricomToken)
    }

    @Update(onConflict = OnConflictStrategy.IGNORE)
    abstract fun update(safaricomToken: SafaricomToken)

    @Query("DELETE FROM SafaricomToken")
    abstract fun deleteAll()
}