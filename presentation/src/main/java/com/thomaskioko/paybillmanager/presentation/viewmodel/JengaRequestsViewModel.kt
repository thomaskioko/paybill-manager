package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.jengatoken.GetJengaToken
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.presentation.mapper.JengaTokenViewMapper
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@VisibleForTesting
class JengaRequestsViewModel @Inject internal constructor(
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
        getJengaToken?.execute(TokenSubscriber())
    }


    inner class TokenSubscriber : DisposableObserver<JengaToken>() {

        override fun onNext(jenga: JengaToken) {
            liveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(jenga), null))
        }

        override fun onComplete() {}

        override fun onError(throwable: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, throwable.localizedMessage))
        }
    }

}