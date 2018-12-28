package com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.GetBillsByCategoryId
import com.thomaskioko.paybillmanager.domain.interactor.billcategory.GetCategoryByBillId
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.mapper.BillViewMapper
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

open class GetBillCategoryViewModel @Inject constructor(
        private val getBillCategory: GetBillsByCategoryId?,
        private val getCategoryByBillId: GetCategoryByBillId?,
        private val billViewMapper: BillViewMapper,
        private val categoryViewMapper: CategoryViewMapper
) : ViewModel() {

    open val billsLiveData: MutableLiveData<Resource<List<BillView>>> = MutableLiveData()
    open val categoryLiveData: MutableLiveData<Resource<CategoryView>> = MutableLiveData()

    override fun onCleared() {
        getBillCategory?.dispose()
        getCategoryByBillId?.dispose()
        super.onCleared()
    }


    open fun getBillsByCategoryId(): LiveData<Resource<List<BillView>>> {
        return billsLiveData
    }

    fun fetchBillsByCategoryId(categoryId: String) {
        billsLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getBillCategory?.execute(
                BillsByCategoryIdSubscriber(),
                GetBillsByCategoryId.Params.forBillsByCategoryId(categoryId)
        )
    }


    open fun getCategoryByBillId(): LiveData<Resource<CategoryView>> {
        return categoryLiveData
    }

    fun fetchCategoryByBillId(billId: String) {
        categoryLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getCategoryByBillId?.execute(
                CategoryByBillIdSubscriber(),
                GetCategoryByBillId.Params.forCategoryByBillId(billId)
        )
    }


    inner class BillsByCategoryIdSubscriber : DisposableObserver<List<Bill>>() {
        override fun onComplete() {}

        override fun onNext(t: List<Bill>) {
            billsLiveData.postValue(Resource(
                    ResourceState.SUCCESS,
                    t.map { billViewMapper.mapToView(it) },
                    null)
            )
        }

        override fun onError(e: Throwable) {
            billsLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

    inner class CategoryByBillIdSubscriber : DisposableObserver<Category>() {

        override fun onNext(t: Category) {
            categoryLiveData.postValue(
                    Resource(ResourceState.SUCCESS, categoryViewMapper.mapToView(t), null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            categoryLiveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}

