package com.thomaskioko.paybillmanager.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.nhaarman.mockitokotlin2.any
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.factory.DataFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment


@RunWith(RobolectricTestRunner::class)
class CachedBillsDaoTest {

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
    fun getBillReturnsData() {
        val bill = BillsCachedFactory.makeCachedBill()
        database.billsDao().insertBill(bill)

        val testObserver = database.billsDao().getBills().test()
        testObserver.assertValue(listOf(bill))
    }

    @Test
    fun getBillByIdReturnsData() {
        val bill = BillsCachedFactory.makeCachedBill()
        database.billsDao().insertBill(bill)

        val testObserver = database.billsDao().getBillById(bill.id).test()
        testObserver.assertValue(bill)
    }

    @Test
    fun getBillsReturnsData() {
        val bill = BillsCachedFactory.makeCachedBill()
        database.billsDao().insertCachedBills(listOf(bill))

        val testObserver = database.billsDao().getBills().test()
        testObserver.assertValue(listOf(bill))
    }

    @Test
    fun getUpdateReturnsData() {
        val bill = BillsCachedFactory.makeCachedBill(DataFactory.randomUuid())
        database.billsDao().updateBill(bill)

        val testObserver = database.billsDao().getBills().test()
        testObserver.assertValue(listOf(bill))

    }

    @Test
    fun deleteBillsClearsData() {
        val project = BillsCachedFactory.makeCachedBill()
        database.billsDao().insertCachedBills(listOf(project))
        database.billsDao().deleteBills()

        val testObserver = database.billsDao().getBills().test()
        testObserver.assertValue(emptyList())
    }
}