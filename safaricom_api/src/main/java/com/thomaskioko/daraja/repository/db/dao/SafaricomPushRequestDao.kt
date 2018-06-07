package com.thomaskioko.daraja.repository.db.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.thomaskioko.daraja.repository.db.entity.SafaricomPushRequest


/**
 * Interface for database access on Request related operations.
 */
@Dao
abstract class SafaricomPushRequestDao {

    @Query("SELECT * FROM SafaricomPushRequest")
    abstract fun findAll(): LiveData<List<SafaricomPushRequest>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg safaricomPushRequests: SafaricomPushRequest)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertSafaricomPushRequest(safaricomPushRequestList: List<SafaricomPushRequest>)

    @Query("DELETE FROM SafaricomPushRequest")
    abstract fun deleteAll()


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun createTokenIfNotExists(safaricomPushRequest: SafaricomPushRequest): Long
}
