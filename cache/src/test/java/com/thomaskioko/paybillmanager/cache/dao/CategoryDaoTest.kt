package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.CategoryCachedFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class CategoryDaoTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categories = listOf(CategoryCachedFactory.makeCachedCategory())
        database.categoryDao().insertCachedCategories(categories)

        val testObserver = database.categoryDao().getCachedCategories().test()
        testObserver.assertValue(categories)
    }

    @Test
    fun getCategoryByIdReturnsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDao().insertCategory(category)

        val testObserver = database.categoryDao().getCachedCategoryById(category.id).test()
        testObserver.assertValue(category)
    }

    @Test
    fun getUpdateCategoryReturnsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDao().updateCachedCategory(category)

        val testObserver = database.categoryDao().getCachedCategoryById(category.id).test()
        testObserver.assertValue(category)

    }

    @Test
    fun deleteCategoriesClearsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDao().insertCachedCategories(listOf(category))
        database.categoryDao().deleteCachedCategories()

        val testObserver = database.categoryDao().getCachedCategories().test()
        testObserver.assertValue(emptyList())
    }
}