package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.factory.CategoryCachedFactory
import com.thomaskioko.paybillmanager.cache.factory.DataFactory
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
        database.categoryDaoDao().insertCachedCategories(categories)

        val testObserver = database.categoryDaoDao().getCachedCategories().test()
        testObserver.assertValue(categories)
    }

    @Test
    fun getCategoryByIdReturnsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDaoDao().insertCachedCategory(category)

        val testObserver = database.categoryDaoDao().getCachedCategoryById(category.id).test()
        testObserver.assertValue(category)
    }

    @Test
    fun getUpdateCategoryReturnsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDaoDao().updateCachedCategory(category)

        val testObserver = database.categoryDaoDao().getCachedCategoryById(category.id).test()
        testObserver.assertValue(category)

    }

    @Test
    fun deleteCategoriesClearsData() {
        val category = CategoryCachedFactory.makeCachedCategory()
        database.categoryDaoDao().insertCachedCategories(listOf(category))
        database.categoryDaoDao().deleteCachedCategories()

        val testObserver = database.categoryDaoDao().getCachedCategories().test()
        testObserver.assertValue(emptyList())
    }
}