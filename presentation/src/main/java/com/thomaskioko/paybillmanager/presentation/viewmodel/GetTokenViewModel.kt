package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.domain.token.GetSafaricomToken
import com.thomaskioko.paybillmanager.presentation.mapper.TokenViewMapper
import com.thomaskioko.paybillmanager.presentation.model.TokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

@VisibleForTesting
class GetTokenViewModel @Inject internal constructor(
        private val getSafaricomToken: GetSafaricomToken?,
        private val mapper: TokenViewMapper
) : ViewModel() {

    private val liveData: MutableLiveData<Resource<TokenView>> = MutableLiveData()


    override fun onCleared() {
        getSafaricomToken?.dispose()
        super.onCleared()
    }

    fun getToken(): LiveData<Resource<TokenView>> {
        return liveData
    }


    fun fetchToken() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getSafaricomToken?.execute(TokenSubscriber())
    }


    inner class TokenSubscriber : DisposableObserver<SafaricomToken>() {

        override fun onNext(t: SafaricomToken) {
            liveData.postValue(Resource(ResourceState.SUCCESS, mapper.mapToView(t), null))
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }
    }

}