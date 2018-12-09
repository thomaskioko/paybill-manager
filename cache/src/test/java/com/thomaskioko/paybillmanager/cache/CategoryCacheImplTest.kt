package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.CategoryCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedCategoryMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CategoryCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val entityMapper = CachedCategoryMapper()
    private val cache = CategoryCacheImpl(database, entityMapper)


    @Test
    fun deletesCategoriesCompletes() {
        val testObserver = cache.deleteCategories().test()
        testObserver.assertComplete()
    }


    @Test
    fun createCategoryCompletes() {
        val categoryEntity = CategoryCachedFactory.makeCategoryEntity()

        val testObserver = cache.createCategory(categoryEntity).test()
        testObserver.assertComplete()
    }

    @Test
    fun createCategoriesCompletes() {
        val categoryEntity = CategoryCachedFactory.makeCategoryEntityList(3)

        val testObserver = cache.createCategories(categoryEntity).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateCategoriesCompletes() {
        val categoryEntity = CategoryCachedFactory.makeCategoryEntity()

        val testObserver = cache.updateCategory(categoryEntity).test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categoryEntity = listOf(CategoryCachedFactory.makeCategoryEntity())
        cache.createCategories(categoryEntity).test()

        val testObserver = cache.getCategories().test()
        testObserver.assertValue(categoryEntity)
    }

    @Test
    fun createCategoryReturnsData() {
        val categoryEntity = CategoryCachedFactory.makeCategoryEntity()
        cache.createCategory(categoryEntity).test()

        val testObserver = cache.getCategoryById(categoryEntity.id).test()
        testObserver.assertValue(categoryEntity)
    }

    @Test
    fun updateCategoryReturnsData() {
        val categoryEntity = CategoryCachedFactory.makeCategoryEntity()
        cache.updateCategory(categoryEntity).test()

        val testObserver = cache.getCategoryById(categoryEntity.id).test()
        testObserver.assertValue(categoryEntity)
    }

}