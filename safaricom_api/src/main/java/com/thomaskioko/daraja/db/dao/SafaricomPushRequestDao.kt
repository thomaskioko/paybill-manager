package com.thomaskioko.daraja.db.dao


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.daraja.db.entity.PushRequestResponse


/**
 * Interface for database access on Request related operations.
 */
@Dao
abstract class SafaricomPushRequestDao {

    @Query("SELECT * FROM PushRequestResponse")
    abstract fun findAll(): LiveData<List<PushRequestResponse>>

    @Query("SELECT * FROM PushRequestResponse WHERE checkoutRequestID = :checkoutRequestID")
    abstract fun findById(checkoutRequestID: String): LiveData<PushRequestResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(vararg pushRequestResponse: PushRequestResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(pushRequestResponseList: List<PushRequestResponse>)

    @Query("DELETE FROM PushRequestResponse")
    abstract fun deleteAll()
}
