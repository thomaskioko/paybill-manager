package com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.GetBillsByCategoryId
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.GetCategoryByBillId
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.presentation.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import junit.framework.TestCase
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class GetBillCategoryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Captor
    val captor = argumentCaptor<DisposableSubscriber<List<Bill>>>()

    @Captor
    val captorCategory = argumentCaptor<DisposableSubscriber<Category>>()


    private val getBillsByCategoryId = mock<GetBillsByCategoryId>()
    private val getCategoryByBillId = mock<GetCategoryByBillId>()
    private val billViewMapper = mock<BillViewMapper>()
    private val categoryViewMapper = mock<CategoryViewMapper>()

    private val viewModel = GetBillCategoryViewModel(
            getBillsByCategoryId, getCategoryByBillId, billViewMapper, categoryViewMapper
    )

    @Test
    fun fetchBillsExecutesUseCase() {
        viewModel.fetchBillsByCategoryId("24")

        //verify that fetch bills use case is called once
        verify(getBillsByCategoryId, times(1)).execute(any(), eq(GetBillsByCategoryId.Params.forBillsByCategoryId("24")))
    }

    @Test
    fun fetchBillsReturnsData() {

        val bills = BillCategoryFactory.makeBillList(2)
        val billView = BillCategoryFactory.makeBillViewList(2)

        stubBillMapperMapToView(billView[0], bills[0])
        stubBillMapperMapToView(billView[1], bills[1])

        viewModel.fetchBillsByCategoryId("24")

        //verify that fetch bills use case is called once
        verify(getBillsByCategoryId)
                .execute(
                        captor.capture(),
                        eq(GetBillsByCategoryId.Params.forBillsByCategoryId("24"))
                )

        //Pass data to onNext callback
        captor.firstValue.onNext(bills)

        //Verify that the data returned is what is returned
        assertEquals(billView, viewModel.getBillsByCategoryId().value?.data)
    }

    @Test
    fun fetchBillByIdReturnsError() {
        //invoke fetchBillsByCategoryId
        viewModel.fetchBillsByCategoryId("24")

        //Use captor to capture the response when execute is called
        verify(getBillsByCategoryId)
                .execute(
                        captor.capture(),
                        eq(GetBillsByCategoryId.Params.forBillsByCategoryId("24"))
                )


        //Pass Exception to onError callback
        captor.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        assertEquals(ResourceState.ERROR, viewModel.getBillsByCategoryId().value?.status)
    }

    @Test
    fun fetchCategoryExecutesUseCase() {
        viewModel.fetchCategoryByBillId("24")

        //verify that fetch bills use case is called once
        verify(getCategoryByBillId, times(1))
                .execute(any(), eq(GetCategoryByBillId.Params.forCategoryByBillId("24")))
    }

    @Test
    fun fetchCategoryByIdReturnsSuccess() {
        val category = CategoryFactory.makeStaticCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubBillMapperMapToView(categoryView, category)

        //invoke fetch fetchCategoryByBillId
        viewModel.fetchCategoryByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryByBillId)
                .execute(
                        captorCategory.capture(),
                        eq(GetCategoryByBillId.Params.forCategoryByBillId("24"))
                )

        //Pass data to onNext callback
        captorCategory.firstValue.onNext(category)

        //Verify that resource type returned is of type success
        assertEquals(ResourceState.SUCCESS, viewModel.getCategoryByBillId().value?.status)
    }

    @Test
    fun fetchCategoryByIdReturnsData() {
        val category = CategoryFactory.makeStaticCategory()
        val categoryView = CategoryFactory.makeCategoryView()
        stubBillMapperMapToView(categoryView, category)

        //invoke fetch fetchCategoryByBillId
        viewModel.fetchCategoryByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryByBillId)
                .execute(
                        captorCategory.capture(),
                        eq(GetCategoryByBillId.Params.forCategoryByBillId("24"))
                )


        //Pass data to onNext callback
        captorCategory.firstValue.onNext(category)

        //Verify that the data returned is what is returned
        TestCase.assertEquals(categoryView, viewModel.getCategoryByBillId().value?.data)
    }


    @Test
    fun fetchCategoryByIdReturnsError() {
        //invoke fetch fetchCategoryByBillId
        viewModel.fetchCategoryByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryByBillId)
                .execute(
                        captorCategory.capture(),
                        eq(GetCategoryByBillId.Params.forCategoryByBillId("24"))
                )

        //Pass Exception to onError callback
        captorCategory.firstValue.onError(RuntimeException())

        //Verify that resource type returned is of type error
        assertEquals(ResourceState.ERROR, viewModel.getCategoryByBillId().value?.status)
    }


    @Test
    fun fetchCategoryByIdReturnsMessage() {
        val errorMessage = "Oops! Something Went wrong"

        //invoke fetch fetchCategoryByBillId
        viewModel.fetchCategoryByBillId("24")

        //Use captor to capture the response when execute is called
        verify(getCategoryByBillId)
                .execute(
                        captorCategory.capture(),
                        eq(GetCategoryByBillId.Params.forCategoryByBillId("24"))
                )

        //Pass error message to onError callback
        captorCategory.firstValue.onError(RuntimeException(errorMessage))

        //Verify that the error message returned is what is expected
        assertEquals(errorMessage, viewModel.getCategoryByBillId().value?.message)
    }

    private fun stubBillMapperMapToView(projectView: BillView, project: Bill) {
        whenever(billViewMapper.mapToView(project)).thenReturn(projectView)
    }

    private fun stubBillMapperMapToView(projectView: CategoryView, project: Category) {
        whenever(categoryViewMapper.mapToView(project)).thenReturn(projectView)
    }

}
