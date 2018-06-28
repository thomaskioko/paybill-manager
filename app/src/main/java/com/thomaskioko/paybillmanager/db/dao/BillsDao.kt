package com.thomaskioko.paybillmanager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.db.entity.Bill
import io.reactivex.Flowable

@Dao
interface BillsDao {

    @Query("SELECT * FROM Bill WHERE paybillNumber = :paybillNumber")
    fun getBillByPayBillNumber(paybillNumber: String): Flowable<List<Bill>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBill(user: Bill)

    @Query("DELETE FROM Bill")
    fun deleteAllBills()
}