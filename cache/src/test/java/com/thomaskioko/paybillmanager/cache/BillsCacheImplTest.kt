package com.thomaskioko.paybillmanager.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillMapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class BillsCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
            RuntimeEnvironment.application.applicationContext,
            PayBillManagerDatabase::class.java)
            .allowMainThreadQueries()
            .build()

    private val entityMapper = CachedBillMapper()
    private val cache = BillsCacheImpl(database, entityMapper)


    @Test
    fun deletesBillsCompletes() {
        val testObserver = cache.deleteBills().test()
        testObserver.assertComplete()
    }


    @Test
    fun createBillCompletes() {
        val bills = BillsCachedFactory.makeBillEntity()

        val testObserver = cache.createBill(bills).test()
        testObserver.assertComplete()
    }

    @Test
    fun createBillsCompletes() {
        val bills = BillsCachedFactory.makeBillEntityList(3)

        val testObserver = cache.createBills(bills).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateBillsCompletes() {
        val bills = BillsCachedFactory.makeBillEntity()

        val testObserver = cache.updateBill(bills).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillsReturnsData() {
        val bills = listOf(BillsCachedFactory.makeBillEntity())
        cache.createBills(bills).test()

        val testObserver = cache.getBills().test()
        testObserver.assertValue(bills)
    }

}