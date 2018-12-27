package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.BillCategoryDataRepository
import com.thomaskioko.paybillmanager.data.mapper.BillCategoryMapper
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryDataStore
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillCategoryDataRepositoryTest {

    private val mapper = mock<BillCategoryMapper>()
    private val factory = mock<BillCategoryDataStoreFactory>()
    private val store = mock<BillCategoryDataStore>()
    private val repository = BillCategoryDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()
    }

    @Test
    fun getBillCategoryComplete() {
        stubGetBillCategory(Flowable.just(BillCategoryFactory.makeBillCategoryEntity()))

        stubMapper(BillCategoryFactory.makeBillCategory(), any())

        val testObserver = repository.getBillCategory("43", "39").test()
        testObserver.assertComplete()
    }

    @Test
    fun createBillCategoryComplete() {

        stubCreateBillCategory(Completable.complete())

        val testObserver = store.createBillCategory(BillCategoryFactory.makeBillCategoryEntity()).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillCategoryReturnsData() {
        val entity = BillCategoryFactory.makeBillCategoryEntity()
        val model = BillCategoryFactory.makeBillCategory()

        stubGetBillCategory(Flowable.just(entity))

        stubMapper(model, entity)

        val testObserver = repository.getBillCategory("43", "39").test()
        testObserver.assertValue(model)

    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubGetBillCategory(observable: Flowable<BillCategoryEntity>) {
        whenever(store.getBillCategory(any(), any()))
                .thenReturn(observable)
    }

    private fun stubCreateBillCategory(completable: Completable) {
        whenever(store.createBillCategory(any()))
                .thenReturn(completable)
    }


    private fun stubMapper(model: BillCategory, entity: BillCategoryEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }
}