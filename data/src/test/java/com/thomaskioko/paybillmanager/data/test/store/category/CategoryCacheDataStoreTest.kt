package com.thomaskioko.paybillmanager.data.test.store.category

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.repository.category.CategoryCache
import com.thomaskioko.paybillmanager.data.store.category.CategoryCacheDataStore
import com.thomaskioko.paybillmanager.data.test.factory.CategoryDataFactory
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryCacheDataStoreTest {

    private val cache = mock<CategoryCache>()
    private val store = CategoryCacheDataStore(cache)

    @Test
    fun getCategoriesCompletes() {

        val observable = Flowable.just(CategoryDataFactory.makeCategoryEntityList(2))

        //Stub getCategories call
        whenever(cache.getCategories()).thenReturn(observable)

        val testObserver = store.getCategories().test()
        testObserver.assertComplete()
    }

    @Test
    fun getCategoriesReturnData() {
        val data = listOf(CategoryDataFactory.makeCategoryEntity())

        whenever(cache.getCategories()).thenReturn(Flowable.just(data))

        //Create test observer
        val testObserver = store.getCategories().test()
        //confirm that the observer completes
        testObserver.assertValue(data)
    }

    @Test
    fun getCategoryByIdCompletes() {

        val observable = Flowable.just(CategoryDataFactory.makeCategoryEntity())

        //Stub getCategories call
        whenever(cache.getCategoryById(any())).thenReturn(observable)

        val testObserver = store.getCategoryById(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }


    @Test
    fun getCategoryByIdReturnData() {
        val data = CategoryDataFactory.makeCategoryEntity()

        whenever(cache.getCategoryById(any())).thenReturn(Flowable.just(data))

        //Create test observer
        val testObserver = store.getCategoryById(DataFactory.randomUuid()).test()
        //confirm that the observer completes
        testObserver.assertValue(data)
    }

    @Test
    fun getCategoriesCallsCacheStore() {
        val data = listOf(CategoryDataFactory.makeCategoryEntity())

        //Stub getCategories
        whenever(cache.getCategories()).thenReturn(Flowable.just(data))

        //Invoke call
        store.getCategories().test()
        //Verify that the right method is called
        verify(cache).getCategories()
    }

    @Test
    fun createCategoryCompletes() {

        //Stub create Category
        whenever(cache.createCategory(any())).thenReturn(Completable.complete())

        val data = CategoryDataFactory.makeCategoryEntity()

        val testObserver = store.createCategory(data).test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun createCategoriesCompletes() {

        //Stub create getCategories
        whenever(cache.createCategories(any())).thenReturn(Completable.complete())

        val data = CategoryDataFactory.makeCategoryEntityList(2)

        val testObserver = store.createCategories(data).test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun updateCategoryCompletes() {
        val data = CategoryDataFactory.makeCategoryEntity()

        //Stub update Category
        whenever(cache.updateCategory(any())).thenReturn(Completable.complete())

        val testObserver = store.updateCategory(data).test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }

    @Test
    fun deleteCategoryCompletes() {

        //Stub delete Category
        whenever(cache.deleteCategories()).thenReturn(Completable.complete())

        val testObserver = store.deleteCategory().test()
        //confirm that the observer completes
        testObserver.assertComplete()
    }


}