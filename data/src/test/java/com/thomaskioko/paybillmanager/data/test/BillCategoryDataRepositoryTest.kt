package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.BillCategoryDataRepository
import com.thomaskioko.paybillmanager.data.mapper.BillCategoryMapper
import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.data.test.factory.CategoryDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillCategoryDataRepositoryTest {

    private val billMapper = mock<BillMapper>()
    private val categoryMapper = mock<CategoryMapper>()
    private val billCategoryMapper = mock<BillCategoryMapper>()
    private val factory = mock<BillCategoryDataStoreFactory>()
    private val cacheDataStore = mock<BillCategoryCacheDataStore>()
    private val repository = BillCategoryDataRepository(billMapper, billCategoryMapper, categoryMapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()

        val category = CategoryDataFactory.makeCategory()
        val categoryEntity = CategoryDataFactory.makeCategoryEntity()

        stubCategoryMapToEntity(category, categoryEntity)
        stubCategoryMapFromEntity(category, categoryEntity)

    }


    @Test
    fun getBillsByCategoryId() {
        stubGetBillsByCategoryId(Flowable.just(BillsDataFactory.makeBillEntityList(3)))

        stubBillMapper(BillsDataFactory.makeBill(), any())

        val testObserver = repository.getBillsByCategoryId("43").test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillCategoryComplete() {
        stubGetBillCategory(Flowable.just(BillCategoryFactory.makeBillCategoryEntity()))

        stubMapper(BillCategoryFactory.makeBillCategory(), any())

        val testObserver = repository.getBillCategory("43", "39").test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoryByBillId() {
        stubGetCategoryByBillId(Flowable.just(CategoryDataFactory.makeCategoryEntity()))

        stubCategoryMapFromEntity(CategoryDataFactory.makeCategory(), any())

        val testObserver = repository.getCategoryByBillId("43").test()
        testObserver.assertComplete()
    }

    @Test
    fun createBillCategoryComplete() {

        val billCategory = BillCategoryFactory.makeBillCategory()
        val billCategoryEntity = BillCategoryFactory.makeBillCategoryEntity()

        stubCreateBillCategory(Completable.complete())

        stubBillCategoryCategoryMapToEntity(billCategory, billCategoryEntity)

        val testObserver = repository.createBillCategory(billCategory).test()

        //Verify that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun getBillsByCategoryIdReturnsData() {
        val entity = BillsDataFactory.makeBillEntity()
        val model = BillsDataFactory.makeBill()

        stubGetBillsByCategoryId(Flowable.just(listOf(entity)))

        stubBillMapper(model, entity)

        val testObserver = repository.getBillsByCategoryId(model.categoryId).test()

        testObserver.assertValue(listOf(model))
    }

    @Test
    fun getCategoryByBillIdReturnsData() {
        val entity = CategoryDataFactory.makeCategoryEntity()
        val model = CategoryDataFactory.makeCategory()

        stubGetCategoryByBillId(Flowable.just(entity))

        stubCategoryMapFromEntity(model, entity)

        val testObserver = repository.getCategoryByBillId(model.id).test()
        testObserver.assertValue(model)
    }

    @Test
    fun getBillCategoryReturnsData() {
        val entity = BillsDataFactory.makeBillEntity()
        val model = BillsDataFactory.makeBill()

        stubGetBillsByCategoryId(Flowable.just(listOf(entity)))

        stubBillMapper(model, entity)

        val testObserver = repository.getBillsByCategoryId(model.categoryId).test()

        testObserver.assertValue(listOf(model))
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubGetBillCategory(observable: Flowable<BillCategoryEntity>) {
        whenever(cacheDataStore.getBillCategory(any(), any()))
                .thenReturn(observable)
    }

    private fun stubCreateBillCategory(completable: Completable) {
        whenever(cacheDataStore.createBillCategory(any()))
                .thenReturn(completable)
    }

    private fun stubBillMapper(model: Bill, entity: BillEntity) {
        whenever(billMapper.mapFromEntity(entity))
                .thenReturn(model)
    }


    private fun stubMapper(model: BillCategory, entity: BillCategoryEntity) {
        whenever(billCategoryMapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubGetBillsByCategoryId(observable: Flowable<List<BillEntity>>) {
        whenever(cacheDataStore.getBillsByCategoryId(any()))
                .thenReturn(observable)
    }

    private fun stubGetCategoryByBillId(observable: Flowable<CategoryEntity>) {
        whenever(cacheDataStore.getCategoryByBillId(any()))
                .thenReturn(observable)
    }

    private fun stubCategoryMapFromEntity(model: Category, entity: CategoryEntity) {
        whenever(categoryMapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubCategoryMapToEntity(model: Category, entity: CategoryEntity) {
        whenever(categoryMapper.mapToEntity(model))
                .thenReturn(entity)
    }

    private fun stubBillCategoryCategoryMapToEntity(model: BillCategory, entity: BillCategoryEntity) {
        whenever(billCategoryMapper.mapToEntity(model))
                .thenReturn(entity)
    }
}