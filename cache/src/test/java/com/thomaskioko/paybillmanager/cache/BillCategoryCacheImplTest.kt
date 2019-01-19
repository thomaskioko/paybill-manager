package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillCategoryCachedFactory
import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillCategoryMapper
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillMapper
import com.thomaskioko.paybillmanager.cache.mapper.CachedCategoryMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class BillCategoryCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val cachedCategoryMapper = CachedCategoryMapper()
    private val cachedBillCategoryMapper = CachedBillCategoryMapper()
    private val cachedBillMapper = CachedBillMapper()

    private val cacheImpl = BillCategoryCacheImpl(database, cachedCategoryMapper, cachedBillCategoryMapper, cachedBillMapper)


    @Test
    fun createBillCategoryCompletes() {

        stubInsertCategoryAndBill()

        val entity = BillCategoryCachedFactory.makeBillCategoryEntity()

        val testObserver = cacheImpl.createBillCategory(entity).test()
        testObserver.assertComplete()

    }

    @Test
    fun updateBillCategoryCompletes() {

        stubInsertCategoryAndBill()

        val entity = BillCategoryCachedFactory.makeBillCategoryEntity()

        val testObserver = cacheImpl.updateBillCategory(entity).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillsByCategoryIdReturnsData() {

        stubInsertCategoryAndBill()

        //Insert BillCategory
        database.billCategoryDao().insertBillCategory(
                BillCategoryCachedFactory.makeCachedBillCategory()
        )

        val testObserver = cacheImpl.getBillsByCategoryId("123").test()
        testObserver.assertValue(listOf(BillsCachedFactory.makeBillEntity()))

    }


    /**
     * Helper function to insert Category and Bill data since Bill category has a foreign key constraint
     */
    private fun stubInsertCategoryAndBill() {
        //Insert Category
        database.categoryDao().insertCategory(BillCategoryCachedFactory.makeCachedCategory())

        //Insert Bill
        database.billsDao().insertBill(BillCategoryCachedFactory.makeCachedBill())
    }

}