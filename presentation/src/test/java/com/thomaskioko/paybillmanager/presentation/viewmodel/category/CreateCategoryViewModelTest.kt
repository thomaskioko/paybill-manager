package com.thomaskioko.paybillmanager.presentation.viewmodel.category

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.category.CreateCategory
import com.thomaskioko.paybillmanager.domain.interactor.category.UpdateCategory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor


@RunWith(JUnit4::class)
class CreateCategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableCompletableObserver>()

    private var mapper = mock<CategoryViewMapper>()
    private var createCategory = mock<CreateCategory>()
    private var updateCategory = mock<UpdateCategory>()
    private var viewModel = CreateCategoryViewModel(createCategory, updateCategory)

    @Test
    fun createCategoryExecutesUseCase() {
        val bill = CategoryFactory.makeStaticCategory()

        //invoke create bill
        viewModel.createCategory(bill)

        //Use captor to capture the response when execute is called
        verify(createCategory).execute(captor.capture(), eq(CreateCategory.Params.forCategory(bill)))
    }

    @Test
    fun updateCategoryExecutesUseCase() {
        val bill = CategoryFactory.makeStaticCategory()

        //invoke update category
        viewModel.updateCategory(bill)

        //Use captor to capture the response when execute is called
        verify(updateCategory).execute(captor.capture(), eq(UpdateCategory.Params.forCategory(bill)))
    }

    @Test
    fun createCategoryReturnsSuccess() {
        val categoryObject = CategoryFactory.makeStaticCategory()
        val category = CategoryFactory.makeCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubCategoryMapperMapToView(categoryView, category)

        //invoke create category
        viewModel.createCategory(CategoryFactory.makeStaticCategory())

        //Use captor to capture the response when execute is called
        verify(createCategory).execute(captor.capture(), eq(CreateCategory.Params.forCategory(categoryObject)))

        //Pass data to onNext callback
        captor.firstValue.onComplete()

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                viewModel.getCategory().value?.status)
    }

    @Test
    fun updateCategoryReturnsSuccess() {
        val categoryObject = CategoryFactory.makeStaticCategory()
        val category = CategoryFactory.makeCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubCategoryMapperMapToView(categoryView, category)

        //invoke update category
        viewModel.updateCategory(CategoryFactory.makeStaticCategory())

        //Use captor to capture the response when execute is called
        verify(updateCategory).execute(captor.capture(), eq(UpdateCategory.Params.forCategory(categoryObject)))

        //Pass data to onNext callback
        captor.firstValue.onComplete()

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                viewModel.getCategory().value?.status)
    }

    @Test
    fun createCategoryReturnsError() {

        val category = CategoryFactory.makeStaticCategory()

        //invoke create bill
        viewModel.createCategory(category)

        //Use captor to capture the response when execute is called
        verify(createCategory).execute(captor.capture(), eq(CreateCategory.Params.forCategory(category)))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getCategory().value?.status)
    }

    @Test
    fun updateCategoryReturnsError() {

        val category = CategoryFactory.makeStaticCategory()

        //invoke update bill
        viewModel.updateCategory(category)

        //Use captor to capture the response when execute is called
        verify(updateCategory).execute(captor.capture(), eq(UpdateCategory.Params.forCategory(category)))

        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getCategory().value?.status)
    }

    @Test
    fun createCategoryMessageForError() {
        val errorMessage = DataFactory.randomString()

        val category = CategoryFactory.makeStaticCategory()

        //invoke create bill
        viewModel.createCategory(category)

        //Use captor to capture the response when execute is called
        verify(createCategory).execute(captor.capture(), eq(CreateCategory.Params.forCategory(category)))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getCategory().value?.message)
    }

    @Test
    fun updateCategoryMessageForError() {
        val errorMessage = DataFactory.randomString()

        val category = CategoryFactory.makeStaticCategory()

        //invoke update bill
        viewModel.updateCategory(category)

        //Use captor to capture the response when execute is called
        verify(updateCategory).execute(captor.capture(), eq(UpdateCategory.Params.forCategory(category)))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getCategory().value?.message)
    }

    private fun stubCategoryMapperMapToView(categoryView: CategoryView, category: Category) {
        whenever(mapper.mapToView(category)).thenReturn(categoryView)
    }

}