package com.thomaskioko.paybillmanager.presentation.viewmodel.category

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.category.GetCategoryById
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@VisibleForTesting
class GetCategoryViewModel @Inject internal constructor(
        private val getCategoryById: GetCategoryById?,
        private val mapper: CategoryViewMapper
) : ViewModel() {

    @VisibleForTesting
    private val liveData: MutableLiveData<Resource<CategoryView>> = MutableLiveData()


    override fun onCleared() {
        getCategoryById?.dispose()
        super.onCleared()
    }

    fun getCategory(): LiveData<Resource<CategoryView>> {

        return liveData
    }


    fun fetchCategory(categoryId: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getCategoryById?.execute(GetCategoryByIdSubscriber(), GetCategoryById.Params.forCategory(categoryId)
        )
    }


    inner class GetCategoryByIdSubscriber : DisposableObserver<Category>() {
        override fun onNext(t: Category) {
            liveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(t), null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }
}