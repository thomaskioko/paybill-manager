package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.interactor.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.interactor.category.GetCategories
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.util.SingleLiveData
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@VisibleForTesting
open class SharedViewModel @Inject internal constructor(
        private val createBill: CreateBill,
        private val updateBill: UpdateBill,
        private val getCategories: GetCategories?,
        private val mapper: CategoryViewMapper
) : ViewModel() {

    val categoryIdLiveData = SingleLiveData<String>()
    val amountLiveData = SingleLiveData<String>()
    val billNameLiveData = SingleLiveData<String>()
    val payBillNumberLiveData = SingleLiveData<String>()
    val billAccountLiveData = SingleLiveData<String>()

    @VisibleForTesting
    val billViewLiveData: MutableLiveData<Resource<BillView>> = MutableLiveData()
    @VisibleForTesting
    open val categoriesLiveData: MutableLiveData<Resource<List<CategoryView>>> = MutableLiveData()


    override fun onCleared() {
        createBill.dispose()
        updateBill.dispose()
        getCategories?.dispose()
        super.onCleared()
    }


    @VisibleForTesting
    open fun getBillLiveData(): LiveData<Resource<BillView>> {
        return billViewLiveData
    }


    fun createBill(bill: Bill) {
        billViewLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return createBill.execute(CreateBillSubscriber(),
                CreateBill.Params.forBill(bill)
        )
    }

    fun updateBill(bill: Bill) {
        billViewLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return updateBill.execute(UpdateBillSubscriber(),
                UpdateBill.Params.forBill(bill)
        )
    }


    fun setAmount(amount: String) {
        amountLiveData.postValue(amount)
    }


    fun setCategoryId(categoryId: String) {
        categoryIdLiveData.value = categoryId
    }

    fun setBillName(billName: String) {
        billNameLiveData.postValue(billName)
    }

    fun setBillAccountNumber(accountNumber: String) {
        billAccountLiveData.postValue(accountNumber)
    }

    fun setPayBillNumber(payBillNumber: String) {
        payBillNumberLiveData.postValue(payBillNumber)
    }

    @VisibleForTesting
    open fun getAmount(): SingleLiveData<String> {
        return amountLiveData
    }

    @VisibleForTesting
    open fun getCategoryId(): SingleLiveData<String> {
        return categoryIdLiveData
    }

    @VisibleForTesting
    open fun getCategories(): LiveData<Resource<List<CategoryView>>> {
        return categoriesLiveData
    }

    @VisibleForTesting
    open fun getBillName(): SingleLiveData<String> {
        return billNameLiveData
    }

    @VisibleForTesting
    open fun getBillAccountNumber(): SingleLiveData<String> {
        return billAccountLiveData
    }

    @VisibleForTesting
    open fun getPayBillNumber(): SingleLiveData<String> {
        return payBillNumberLiveData
    }


    fun fetchCategories() {
        categoriesLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getCategories?.execute(CategoriesSubscriber())
    }


    inner class CategoriesSubscriber : DisposableObserver<List<Category>>() {
        override fun onNext(t: List<Category>) {
            categoriesLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            categoriesLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }


    inner class CreateBillSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            billViewLiveData.postValue(Resource(ResourceState.SUCCESS, billViewLiveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            billViewLiveData.postValue(Resource(ResourceState.ERROR, billViewLiveData.value?.data, e.localizedMessage))
        }
    }


    inner class UpdateBillSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            billViewLiveData.postValue(Resource(ResourceState.SUCCESS, billViewLiveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            billViewLiveData.postValue(Resource(ResourceState.ERROR, billViewLiveData.value?.data, e.localizedMessage))
        }
    }
}