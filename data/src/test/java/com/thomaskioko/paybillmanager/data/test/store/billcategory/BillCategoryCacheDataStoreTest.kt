package com.thomaskioko.paybillmanager.data.test.store.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillsDataFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillCategoryCacheDataStoreTest {

    private val cache = mock<BillCategoryCache>()
    private val store = BillCategoryCacheDataStore(cache)


    @Test
    fun getBillCategoryCompletes() {

        stubBillCategoryEntity(Flowable.just(BillCategoryFactory.makeBillCategoryEntity()))

        val testObserver = store.getBillCategory("343", "434").test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillCategoryReturnsData() {

        val entity = BillCategoryFactory.makeBillCategoryEntity()

        stubBillCategoryEntity(Flowable.just(entity))

        val testObserver = store.getBillCategory("343", "434").test()
        testObserver.assertValue(entity)
    }

    @Test
    fun createBillCategoryCompletes() {

        //Stub create bill
        whenever(cache.createBillCategory(any())).thenReturn(Completable.complete())

        val testObserver = store.createBillCategory(BillCategoryFactory.makeBillCategoryEntity()).test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun updateBillCategoryCompletes() {
        val entity = BillCategoryFactory.makeBillCategoryEntity()

        //Stub updateBill
        whenever(cache.updateBillCategory(any())).thenReturn(Completable.complete())

        val testObserver = store.updateBillCategory(entity).test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }


    private fun stubBillCategoryEntity(observable: Flowable<BillCategoryEntity>) {
        whenever(cache.getBillCategory(any(), any())).thenReturn(observable)
    }

}