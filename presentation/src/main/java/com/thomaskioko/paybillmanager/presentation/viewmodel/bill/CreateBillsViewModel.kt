package com.thomaskioko.paybillmanager.presentation.viewmodel.bill

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.bills.CreateBill
import com.thomaskioko.paybillmanager.domain.interactor.bills.UpdateBill
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

@VisibleForTesting
open class CreateBillsViewModel @Inject internal constructor(
        private val createBill: CreateBill,
        private val updateBill: UpdateBill
) : ViewModel() {

    val categoryIdLiveData = MutableLiveData<String>()
    val amountLiveData = MutableLiveData<String>()
    @VisibleForTesting
    val billViewLiveData: MutableLiveData<Resource<BillView>> = MutableLiveData()


    override fun onCleared() {
        createBill.dispose()
        super.onCleared()
    }


    @VisibleForTesting
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

    @VisibleForTesting
    open fun getAmount(): MutableLiveData<String> {
        return amountLiveData
    }

    @VisibleForTesting
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