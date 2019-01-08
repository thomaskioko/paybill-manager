package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.jengatoken.GetJengaToken
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.presentation.mapper.JengaTokenViewMapper
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

@VisibleForTesting
open class JengaRequestsViewModel @Inject internal constructor(
        private val getJengaToken: GetJengaToken?,
        private val mapper: JengaTokenViewMapper
) : ViewModel() {

    open val liveData: MutableLiveData<Resource<JengaTokenView>> = MutableLiveData()

    override fun onCleared() {
        getJengaToken?.dispose()
        super.onCleared()
    }

    open fun getJengaToken(): LiveData<Resource<JengaTokenView>> {
        return liveData
    }


    fun fetchJengaToken() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getJengaToken?.execute(JengaTokenSubscriber())
    }


    inner class JengaTokenSubscriber : DisposableSubscriber<JengaToken>() {

        override fun onNext(jenga: JengaToken) {
            liveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(jenga), null))
        }

        override fun onComplete() {}

        override fun onError(throwable: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, throwable.localizedMessage))
        }
    }

}