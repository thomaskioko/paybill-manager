package com.thomaskioko.paybillmanager.presentation.viewmodel.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.category.GetCategories
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class GetCategoriesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Category>>>()

    private var getCategories = mock<GetCategories>()
    private var mapper = mock<CategoryViewMapper>()
    private var categoryViewModel = GetCategoriesViewModel(getCategories, mapper)


    @Test
    fun fetchCategoriesExecutesUseCase() {
        categoryViewModel.fetchCategories()

        //verify that fetch bill by id use case is called once
        verify(getCategories, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchCategoriesReturnsSuccess() {
        val category = CategoryFactory.makeCategoryList(3)
        val categoryView = CategoryFactory.makeCategoryViewList(3)
        stubCategoryMapperMapToView(categoryView[0], category[0])

        //invoke fetch fetchBillById
        categoryViewModel.fetchCategories()

        //Use captor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, categoryViewModel.getCategories().value?.status)
    }


    @Test
    fun fetchCategoriesReturnsData() {
        val category = CategoryFactory.makeCategoryList(2)
        val categoryView = CategoryFactory.makeCategoryViewList(2)
        stubCategoryMapperMapToView(categoryView[0], category[0])
        stubCategoryMapperMapToView(categoryView[1], category[1])

        //invoke fetch fetchCategory
        categoryViewModel.fetchCategories()

        //Use captor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(categoryView, categoryViewModel.getCategories().value?.data)
    }


    @Test
    fun fetchCategoriesReturnsError() {
        //invoke fetch bill by id
        categoryViewModel.fetchCategories()

        //Use captor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, categoryViewModel.getCategories().value?.status)
    }


    @Test
    fun fetchCategoriesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch bill by id
        categoryViewModel.fetchCategories()

        //Use captor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, categoryViewModel.getCategories().value?.message)
    }

    private fun stubCategoryMapperMapToView(projectView: CategoryView, project: Category) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }

}