package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.CategoryDataRepository
import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.category.CategoryDataStore
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
    private val store = mock<CategoryDataStore>()
    private val repository = CategoryDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()
    }

    @Test
    fun getCategoriesCompletes() {
        stubGetCategories(Flowable.just(CategoryDataFactory.makeCategoryEntityList(3)))

        stubMapper(CategoryDataFactory.makeCategory(), any())

        val testObserver = repository.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnsData() {
        val categoryEntity = CategoryDataFactory.makeCategoryEntity()
        val category = CategoryDataFactory.makeCategory()

        stubGetCategories(Flowable.just(listOf(categoryEntity)))

        stubMapper(category, categoryEntity)

        val testObserver = repository.getCategories().test()
        testObserver.assertValue(listOf(category))
    }


    @Test
    fun createCategoryCompletes() {

        stubCreateCategory(Completable.complete())

        val testObserver = store.createCategory(CategoryDataFactory.makeCategoryEntity()).test()
        testObserver.assertComplete()
    }

    @Test
    fun createCategoriesCompletes() {

        stubCreateCategories(Completable.complete())

        val testObserver = store.createCategories(CategoryDataFactory.makeCategoryEntityList(3)).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateCategoryCompletes() {

        stubUpdateCategory(Completable.complete())

        val testObserver = store.updateCategory(CategoryDataFactory.makeCategoryEntity()).test()
        testObserver.assertComplete()
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubCreateCategory(completable: Completable) {
        whenever(store.createCategory(any()))
                .thenReturn(completable)
    }

    private fun stubCreateCategories(completable: Completable) {
        whenever(store.createCategories(any()))
                .thenReturn(completable)
    }

    private fun stubUpdateCategory(completable: Completable) {
        whenever(store.updateCategory(any()))
                .thenReturn(completable)
    }

    private fun stubGetCategories(observable: Flowable<List<CategoryEntity>>) {
        whenever(store.getCategories()).thenReturn(observable)
    }

    private fun stubMapper(model: Category, entity: CategoryEntity) {
        whenever(mapper.mapFromEntity(entity)).thenReturn(model)
    }

}