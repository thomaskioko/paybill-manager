package com.thomaskioko.paybillmanager.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.db.entity.BillCategory
import io.reactivex.Flowable

@Dao
interface BillCategoryDao {

    @Query("SELECT * FROM BillCategory WHERE categoryName = :categoryName")
    fun getBillCategoryByName(categoryName: String): Flowable<BillCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBillCategory(billCategory: BillCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBillCategories(billCategory: List<BillCategory>)

    @Query("DELETE FROM BillCategory")
    fun deleteAllBillCategories()

}