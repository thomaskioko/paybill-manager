package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import io.reactivex.Flowable


@Dao
abstract class CategoryDao {

    @Query("SELECT * FROM category ORDER BY categoryName DESC")
    @JvmSuppressWildcards
    abstract fun getCachedCategories(): Flowable<List<CachedCategory>>

    @Query("SELECT * FROM category where id = :categoryId")
    @JvmSuppressWildcards
    abstract fun getCachedCategoryById(categoryId: String): Flowable<CachedCategory>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertCachedCategories(categories: List<CachedCategory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertCachedCategory(cachedCategory: CachedCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun updateCachedCategory(cachedCategory: CachedCategory)

    @Query("DELETE FROM category")
    abstract fun deleteCachedCategories()

}