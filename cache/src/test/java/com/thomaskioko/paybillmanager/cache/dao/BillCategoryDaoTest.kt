package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillCategoryCachedFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class BillCategoryDaoTest {

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
    fun getBillsReturnsData() {
        stubInsertData()

        val billCategory = BillCategoryCachedFactory.makeCachedBillCategory()
        database.billCategoryDao().insertBillCategory(billCategory)

        val testObserver = database.billCategoryDao().getBills("123").test()
        testObserver.assertValue(listOf(BillCategoryCachedFactory.makeCachedBill()))
    }

    @Test
    fun getBillCategoryReturnData() {
        stubInsertData()

        val billCategory = BillCategoryCachedFactory.makeCachedBillCategory()
        //Insert data into the table
        database.billCategoryDao().insertBillCategory(billCategory)


        val testObserver = database.billCategoryDao().getBillCategory(billCategory.billId, billCategory.categoryId).test()

        //Verify that the data returned is correct
        testObserver.assertValue(billCategory)
    }


    @Test
    fun getCategoryReturnsData() {
        stubInsertData()

        val billCategory = BillCategoryCachedFactory.makeCachedBillCategory()
        database.billCategoryDao().insertBillCategory(billCategory)

        val testObserver = database.billCategoryDao().getCategory("148").test()
        testObserver.assertValue(BillCategoryCachedFactory.makeCachedCategory())
    }

    private fun stubInsertData() {
        database.categoryDao().insertCategory(BillCategoryCachedFactory.makeCachedCategory())
        database.billsDao().insertBill(BillCategoryCachedFactory.makeCachedBill())

    }

}