package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.CategoryDataRepository
import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.store.category.CategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.store.category.CategoryDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.CategoryDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryDataRepositoryTest {

    private val mapper = mock<CategoryMapper>()
    private val factory = mock<CategoryDataStoreFactory>()
    private val cacheDataStore = mock<CategoryCacheDataStore>()
    private val repository = CategoryDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()
    }

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Flowable.just(CategoryDataFactory.makeCategoryEntityList(3)))

        stubCategoryMapFromEntity(CategoryDataFactory.makeCategory(), any())

        val testObserver = repository.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categoryEntity = CategoryDataFactory.makeCategoryEntity()
        val category = CategoryDataFactory.makeCategory()

        stubGetCategories(Flowable.just(listOf(categoryEntity)))

        stubCategoryMapFromEntity(category, categoryEntity)

        val testObserver = repository.getCategories().test()
        testObserver.assertValue(listOf(category))
    }

    @Test
    fun getCategoryByIdCompletes() {
        val categoryEntity = CategoryDataFactory.makeCategoryEntity()
        stubGetCategoryById(Flowable.just(categoryEntity))

        stubCategoryMapFromEntity(CategoryDataFactory.makeCategory(), any())

        val testObserver = repository.getCategoryById(categoryEntity.id).test()
        testObserver.assertComplete()
    }


    @Test
    fun createCategoryCompletes() {

        val categoryEntity = CategoryDataFactory.makeCategoryEntity()
        val category = CategoryDataFactory.makeCategory()

        stubCategoryMapToEntity(category, categoryEntity)

        stubCreateCategory(Completable.complete())

        val testObserver = repository.createCategory(category).test()
        testObserver.assertComplete()
    }

    @Test
    fun createCategoriesCompletes() {

        stubCreateCategories(Completable.complete())

        val testObserver = repository.createCategories(CategoryDataFactory.makeCategoryList(3)).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateCategoryCompletes() {

        val categoryEntity = CategoryDataFactory.makeCategoryEntity()
        val category = CategoryDataFactory.makeCategory()

        stubCategoryMapToEntity(category, categoryEntity)

        stubUpdateCategory(Completable.complete())

        val testObserver = repository.updateCategory(category).test()
        testObserver.assertComplete()
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubCreateCategory(completable: Completable) {
        whenever(cacheDataStore.createCategory(any()))
                .thenReturn(completable)
    }

    private fun stubCreateCategories(completable: Completable) {
        whenever(cacheDataStore.createCategories(any()))
                .thenReturn(completable)
    }

    private fun stubUpdateCategory(completable: Completable) {
        whenever(cacheDataStore.updateCategory(any()))
                .thenReturn(completable)
    }

    private fun stubGetCategoryById(observable: Flowable<CategoryEntity>) {
        whenever(cacheDataStore.getCategoryById(any()))
                .thenReturn(observable)
    }

    private fun stubGetCategories(observable: Flowable<List<CategoryEntity>>) {
        whenever(cacheDataStore.getCategories())
                .thenReturn(observable)
    }

    private fun stubCategoryMapFromEntity(model: Category, entity: CategoryEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubCategoryMapToEntity(model: Category, entity: CategoryEntity) {
        whenever(mapper.mapToEntity(model))
                .thenReturn(entity)
    }

}