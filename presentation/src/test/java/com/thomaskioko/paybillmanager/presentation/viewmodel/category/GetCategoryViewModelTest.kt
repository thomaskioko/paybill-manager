package com.thomaskioko.paybillmanager.presentation.viewmodel.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.category.GetCategoryById
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
class GetCategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableObserver<Category>>()

    private var getCategoryById = mock<GetCategoryById>()
    private var mapper = mock<CategoryViewMapper>()
    private var categoryViewModel = GetCategoryViewModel(getCategoryById, mapper)



    @Test
    fun fetchBillExecutesUseCase() {
        categoryViewModel.fetchCategory("24")

        //verify that fetch bill by id use case is called once
        verify(getCategoryById, times(1)).execute(any(), eq(GetCategoryById.Params.forCategory("24")))
    }

    @Test
    fun fetchCategoryByIdReturnsSuccess() {
        val category = CategoryFactory.makeStaticCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubBillMapperMapToView(categoryView, category)

        //invoke fetch fetchBillByBillId
        categoryViewModel.fetchCategory("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryById).execute(captor.capture(),eq(GetCategoryById.Params.forCategory("24")))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, categoryViewModel.getCategory().value?.status)
    }



    @Test
    fun fetchCategoryByIdReturnsData() {
        val category = CategoryFactory.makeStaticCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubBillMapperMapToView(categoryView, category)

        //invoke fetch fetchCategory
        categoryViewModel.fetchCategory("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryById).execute(captor.capture(),eq(GetCategoryById.Params.forCategory("24")))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(categoryView, categoryViewModel.getCategory().value?.data)
    }


    @Test
    fun fetchCategoryByIdReturnsError() {
        //invoke fetch bill by id
        categoryViewModel.fetchCategory("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryById).execute(captor.capture(), eq(GetCategoryById.Params.forCategory("24")))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, categoryViewModel.getCategory().value?.status)
    }


    @Test
    fun fetchCategoryByIdReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch bill by id
        categoryViewModel.fetchCategory("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryById).execute(captor.capture(), eq(GetCategoryById.Params.forCategory("24")))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, categoryViewModel.getCategory().value?.message)
    }

    private fun stubBillMapperMapToView(projectView: CategoryView, project: Category) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }

}