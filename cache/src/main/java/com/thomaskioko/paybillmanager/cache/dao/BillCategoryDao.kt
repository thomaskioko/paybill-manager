package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedBill
import com.thomaskioko.paybillmanager.cache.model.CachedBillCategory
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import io.reactivex.Flowable

@Dao
abstract class BillCategoryDao {

    @Insert
    @JvmSuppressWildcards
    abstract fun insertBillCategory(cachedBillCategory: CachedBillCategory)

    @Query("SELECT * FROM bill_category_join WHERE billId=:billId AND categoryId=:categoryId")
    abstract fun getBillCategory(billId: String, categoryId: String): Flowable<CachedBillCategory>

    @Query("SELECT * FROM bill INNER JOIN bill_category_join ON " +
            "bill.id=bill_category_join.billId WHERE bill_category_join.categoryId=:categoryId")
    abstract fun getBills(categoryId: String): Flowable<List<CachedBill>>

    @Query("SELECT * FROM category INNER JOIN bill_category_join ON " +
            "category.id=bill_category_join.categoryId WHERE bill_category_join.billId=:billId")
    abstract fun getCategory(billId: String): Flowable<CachedCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateBillCategory(cachedBillCategory: CachedBillCategory)

}