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

    private val liveData: MutableLiveData<Resource<BillView>> = MutableLiveData()


    override fun onCleared() {
        createBill.dispose()
        super.onCleared()
    }


    fun getBill(): LiveData<Resource<BillView>> {
        return liveData
    }


    fun createBill(bill: Bill) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return createBill.execute(CreateBillSubscriber(),
                CreateBill.Params.forBill(bill)
        )
    }

    fun updateBill(bill: Bill) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        return updateBill.execute(UpdateBillSubscriber(),
                UpdateBill.Params.forBill(bill)
        )
    }



    inner class CreateBillSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data, e.localizedMessage))
        }
    }


    inner class UpdateBillSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            liveData.postValue(Resource(ResourceState.SUCCESS, liveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, liveData.value?.data, e.localizedMessage))
        }
    }
}