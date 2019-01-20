package com.thomaskioko.paybillmanager.data.test.store.billcategory

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.data.test.factory.CategoryDataFactory
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
    fun getBillsByCategoryIdCompletes() {

        stubBillsByCategoryId(Flowable.just(BillsDataFactory.makeBillEntityList(3)))

        val testObserver = store.getBillsByCategoryId("343").test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillsByCategoryIdReturnsData() {

        val entity = BillsDataFactory.makeBillEntityList(3)

        stubBillsByCategoryId(Flowable.just(entity))

        val testObserver = store.getBillsByCategoryId("343").test()
        testObserver.assertValue(entity)
    }

    @Test
    fun getCategoryByBillIdCompletes() {
        val entity = CategoryDataFactory.makeCategoryEntity()

        stubCategoryByBillId(Flowable.just(entity))

        val testObserver = store.getCategoryByBillId("343").test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoryByBillIdReturnsData() {

        val entity = CategoryDataFactory.makeCategoryEntity()

        stubCategoryByBillId(Flowable.just(entity))

        val testObserver = store.getCategoryByBillId("343").test()
        testObserver.assertValue(entity)
    }

    @Test
    fun getBillCategoryCompletes() {

        val entity = BillCategoryFactory.makeBillCategoryEntity()
        stubBillCategory(Flowable.just(entity))

        val testObserver = store.getBillCategory(entity.billId, entity.categoryId).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillCategoryCompletesReturnsData() {

        val entity = BillCategoryFactory.makeBillCategoryEntity()
        stubBillCategory(Flowable.just(entity))

        val testObserver = store.getBillCategory(entity.billId, entity.categoryId).test()
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


    private fun stubBillsByCategoryId(observable: Flowable<List<BillEntity>>) {
        whenever(cache.getBillsByCategoryId(any())).thenReturn(observable)
    }

    private fun stubCategoryByBillId(observable: Flowable<CategoryEntity>) {
        whenever(cache.getCategoryByBillId(any())).thenReturn(observable)
    }

    private fun stubBillCategory(observable: Flowable<BillCategoryEntity>) {
        whenever(cache.getBillCategory(any(), any())).thenReturn(observable)
    }

}