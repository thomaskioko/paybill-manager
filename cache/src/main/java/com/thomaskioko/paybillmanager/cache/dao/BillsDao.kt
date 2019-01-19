package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedBill
import io.reactivex.Flowable


@Dao
abstract class BillsDao {

    @Query("SELECT * FROM bill")
    @JvmSuppressWildcards
    abstract fun getBills(): Flowable<List<CachedBill>>

    @Query("SELECT * FROM bill where id = :billId")
    @JvmSuppressWildcards
    abstract fun getBillByBillId(billId: String): Flowable<CachedBill>

    @Query("SELECT * FROM bill where id = :billId AND categoryId=:categoryId")
    @JvmSuppressWildcards
    abstract fun getBillByIds(billId: String, categoryId: String): Flowable<CachedBill>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertCachedBills(cachedBills: List<CachedBill>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertBill(cachedBill: CachedBill)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateBill(cachedBill: CachedBill)

    @Query("DELETE FROM bill")
    abstract fun deleteBills()

}