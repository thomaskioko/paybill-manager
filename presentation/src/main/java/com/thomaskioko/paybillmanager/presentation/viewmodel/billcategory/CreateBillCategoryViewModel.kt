package com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.CreateBillsCategory
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.presentation.model.BillCategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import javax.inject.Inject

open class CreateBillCategoryViewModel @Inject constructor(
        private val createBillsCategory: CreateBillsCategory?
) : ViewModel() {

    open val billCategoryLiveData: MutableLiveData<Resource<BillCategoryView>> = MutableLiveData()

    override fun onCleared() {
        createBillsCategory?.dispose()
        super.onCleared()
    }

    open fun getBillCategory(): LiveData<Resource<BillCategoryView>> {
        return billCategoryLiveData
    }

    fun createBillCategory(billCategory: BillCategory) {
        billCategoryLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return createBillsCategory?.execute(
                CreateBillCategorySubscriber(),
                CreateBillsCategory.Params.forBillCategory(billCategory)
        )!!
    }

    inner class CreateBillCategorySubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            billCategoryLiveData.postValue(Resource(ResourceState.SUCCESS, billCategoryLiveData.value?.data, null))
        }

        override fun onError(e: Throwable) {
            billCategoryLiveData.postValue(Resource(ResourceState.ERROR, billCategoryLiveData.value?.data, e.localizedMessage))
        }
    }
}
