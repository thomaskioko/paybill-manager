package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.interactor.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.interactor.category.GetCategories
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class SharedViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val disposableCaptor = argumentCaptor<DisposableCompletableObserver>()
    @Captor
    val captor = argumentCaptor<DisposableObserver<List<Category>>>()

    private var mapper = mock<CategoryViewMapper>()
    private var billViewMapper = mock<BillViewMapper>()
    private var createBill = mock<CreateBill>()
    private var updateBill = mock<UpdateBill>()
    private var getCategories = mock<GetCategories>()

    private var viewModel = SharedViewModel(createBill, updateBill, getCategories, mapper)

    @Test
    fun createBillExecutesUseCase() {
        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        viewModel.createBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(createBill).execute(disposableCaptor.capture(), eq(CreateBill.Params.forBill(bill)))
    }

    @Test
    fun updateBillExecutesUseCase() {
        val bill = BillsFactory.makeStaticBill()

        //invoke update bill
        viewModel.updateBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(updateBill).execute(disposableCaptor.capture(), eq(UpdateBill.Params.forBill(bill)))
    }

    @Test
    fun createBillReturnsSuccess() {
        val billObject = BillsFactory.makeStaticBill()
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke create bill
        viewModel.createBill(BillsFactory.makeStaticBill())

        //Use disposableCaptor to capture the response when execute is called
        verify(createBill).execute(disposableCaptor.capture(), eq(CreateBill.Params.forBill(billObject)))

        //Pass data to onNext callback
        disposableCaptor.firstValue.onComplete()

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                viewModel.getBillLiveData().value?.status)
    }

    @Test
    fun updateBillReturnsSuccess() {
        val billObject = BillsFactory.makeStaticBill()
        val bill = BillsFactory.makeBill()
        val billView = BillsFactory.makeBillView()
        stubBillMapperMapToView(billView, bill)

        //invoke update bill
        viewModel.updateBill(BillsFactory.makeStaticBill())

        //Use disposableCaptor to capture the response when execute is called
        verify(updateBill).execute(disposableCaptor.capture(), eq(UpdateBill.Params.forBill(billObject)))

        //Pass data to onNext callback
        disposableCaptor.firstValue.onComplete()

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS,
                viewModel.getBillLiveData().value?.status)
    }

    @Test
    fun createBillReturnsError() {

        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        viewModel.createBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(createBill).execute(disposableCaptor.capture(), eq(CreateBill.Params.forBill(bill)))


        //Pass Exception to onError callback
        disposableCaptor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getBillLiveData().value?.status)
    }

    @Test
    fun updateBillReturnsError() {

        val bill = BillsFactory.makeStaticBill()

        //invoke update bill
        viewModel.updateBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(updateBill).execute(disposableCaptor.capture(), eq(UpdateBill.Params.forBill(bill)))

        //Pass Exception to onError callback
        disposableCaptor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getBillLiveData().value?.status)
    }

    @Test
    fun createBillMessageForError() {
        val errorMessage = DataFactory.randomString()

        val bill = BillsFactory.makeStaticBill()

        //invoke create bill
        viewModel.createBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(createBill).execute(disposableCaptor.capture(), eq(CreateBill.Params.forBill(bill)))


        //Pass error message to onError callback
        disposableCaptor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getBillLiveData().value?.message)
    }

    @Test
    fun updateBillMessageForError() {
        val errorMessage = DataFactory.randomString()

        val bill = BillsFactory.makeStaticBill()

        //invoke update bill
        viewModel.updateBill(bill)

        //Use disposableCaptor to capture the response when execute is called
        verify(updateBill).execute(disposableCaptor.capture(), eq(UpdateBill.Params.forBill(bill)))


        //Pass error message to onError callback
        disposableCaptor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getBillLiveData().value?.message)
    }


    @Test
    fun setAmountReturnsRightValue(){
        viewModel.setAmount("2300")

        TestCase.assertEquals(viewModel.amountLiveData.value, "2300")
    }


    @Test
    fun setCategoryIdReRightValue(){
        viewModel.setCategoryId("2")

        TestCase.assertEquals(viewModel.categoryIdLiveData.value, "2")
    }

    @Test
    fun fetchCategoriesExecutesUseCase() {
        viewModel.fetchCategories()

        //verify that fetch bill by id use case is called once
        verify(getCategories, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchCategoriesReturnsSuccess() {
        val category = CategoryFactory.makeCategoryList(3)
        val categoryView = CategoryFactory.makeCategoryViewList(3)
        stubCategoryMapperMapToView(categoryView[0], category[0])

        //invoke fetch fetchBillById
        viewModel.fetchCategories()

        //Use disposableCaptor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that resource type returned is of type success
        TestCase.assertEquals(ResourceState.SUCCESS, viewModel.getCategories().value?.status)
    }


    @Test
    fun fetchCategoriesReturnsData() {
        val category = CategoryFactory.makeCategoryList(2)
        val categoryView = CategoryFactory.makeCategoryViewList(2)
        stubCategoryMapperMapToView(categoryView[0], category[0])
        stubCategoryMapperMapToView(categoryView[1], category[1])

        //invoke fetch fetchCategory
        viewModel.fetchCategories()

        //Use disposableCaptor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))

        //Pass data to onNext callback
        captor.firstValue.onNext(category)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(categoryView, viewModel.getCategories().value?.data)
    }


    @Test
    fun fetchCategoriesReturnsError() {
        //invoke fetch bill by id
        viewModel.fetchCategories()

        //Use disposableCaptor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        TestCase.assertEquals(ResourceState.ERROR, viewModel.getCategories().value?.status)
    }


    @Test
    fun fetchCategoriesReturnsMessageForError() {
        val errorMessage = DataFactory.randomString()

        //invoke fetch bill by id
        viewModel.fetchCategories()

        //Use disposableCaptor to capture the response when execute is called
        verify(getCategories).execute(captor.capture(), eq(null))


        //Pass error message to onError callback
        captor.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        TestCase.assertEquals(errorMessage, viewModel.getCategories().value?.message)
    }

    private fun stubCategoryMapperMapToView(projectView: CategoryView, project: Category) {
        whenever(mapper.mapToView(project)).thenReturn(projectView)
    }

    private fun stubBillMapperMapToView(projectView: BillView, project: Bill) {
        whenever(billViewMapper.mapToView(project)).thenReturn(projectView)
    }


}