package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.bills.GetBillById
import com.thomaskioko.paybillmanager.domain.bills.GetBills
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@VisibleForTesting
class GetBillsViewModel @Inject internal constructor(
        private val getBillById: GetBillById?,
        private val getBills: GetBills?,
        private val mapper: BillViewMapper
) : ViewModel() {

    @VisibleForTesting
    val billsLiveData: MutableLiveData<Resource<List<BillView>>> = MutableLiveData()
    @VisibleForTesting
    val billLiveData: MutableLiveData<Resource<BillView>> = MutableLiveData()


    override fun onCleared() {
        getBills?.dispose()
        getBillById?.dispose()
        super.onCleared()
    }

    @VisibleForTesting
    fun getBills(): LiveData<Resource<List<BillView>>> {
        return billsLiveData
    }

    fun getBill(): LiveData<Resource<BillView>> {
        return billLiveData
    }

    fun fetchBills() {
        billsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBills?.execute(BillsSubscriber())
    }


    fun fetchBillById(billId: String) {
        billLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBillById?.execute(BillByIdSubscriber(), GetBillById.Params.forBill(billId))
    }


    inner class BillsSubscriber : DisposableObserver<List<Bill>>() {
        override fun onNext(t: List<Bill>) {
            billsLiveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            billsLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

    inner class BillByIdSubscriber : DisposableObserver<Bill>() {
        override fun onNext(t: Bill) {
            billLiveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(t), null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            billLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

}