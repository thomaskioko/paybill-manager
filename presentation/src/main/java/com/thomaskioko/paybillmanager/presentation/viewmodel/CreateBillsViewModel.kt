package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

open class CreateBillsViewModel @Inject internal constructor(
        private val createBill: CreateBill,
        private val updateBill: UpdateBill
) : ViewModel() {

    val categoryIdLiveData = MutableLiveData<String>()
    val amountLiveData = MutableLiveData<String>()
    open val billViewLiveData: MutableLiveData<Resource<BillView>> = MutableLiveData()


    override fun onCleared() {
        createBill.dispose()
        super.onCleared()
    }


    open fun getBill(): LiveData<Resource<BillView>> {
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
        amountLiveData.value = amount
    }


    fun setCategoryId(categoryId: String) {
        categoryIdLiveData.value = categoryId
    }

    open fun getAmount(): MutableLiveData<String> {
        return amountLiveData
    }

    open fun getCategoryId(): MutableLiveData<String> {
        return categoryIdLiveData
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