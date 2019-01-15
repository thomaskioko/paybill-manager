package com.thomaskioko.paybillmanager.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thomaskioko.paybillmanager.domain.interactor.jengatoken.GetJengaToken
import com.thomaskioko.paybillmanager.domain.interactor.mpesarequest.GetMpesaStkPush
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.presentation.mapper.JengaTokenViewMapper
import com.thomaskioko.paybillmanager.presentation.mapper.MpesaPushViewMapper
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.model.MpesaPushResponseView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

@VisibleForTesting
open class JengaRequestsViewModel @Inject internal constructor(
        private val getJengaToken: GetJengaToken?,
        private val getMpesaStkPush: GetMpesaStkPush?,
        private val jengaTokenViewMapper: JengaTokenViewMapper,
        private val mpesaPushViewMapper: MpesaPushViewMapper
) : ViewModel() {

    open val liveData: MutableLiveData<Resource<JengaTokenView>> = MutableLiveData()
    open val mpesaStkLiveData: MutableLiveData<Resource<MpesaPushResponseView>> = MutableLiveData()

    override fun onCleared() {
        getJengaToken?.dispose()
        getMpesaStkPush?.dispose()
        super.onCleared()
    }

    open fun getJengaToken(): LiveData<Resource<JengaTokenView>> {
        return liveData
    }

    fun fetchJengaToken() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getJengaToken?.execute(JengaTokenSubscriber())
    }


    open fun getMpesaPushResponse(): LiveData<Resource<MpesaPushResponseView>> {
        return mpesaStkLiveData
    }


    fun fetchMpesaPushResponse(bearer: String, signaturePayload: String) {
        mpesaStkLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        getMpesaStkPush?.execute(
                JengaMpesaStkSubscriber(),
                GetMpesaStkPush.Params.forGetMpesaPushRequest(bearer, signaturePayload)
        )
    }


    inner class JengaTokenSubscriber : DisposableSubscriber<JengaToken>() {

        override fun onNext(jenga: JengaToken) {
            liveData.postValue(Resource(ResourceState.SUCCESS, jengaTokenViewMapper.mapToView(jenga), null))
        }

        override fun onComplete() {}

        override fun onError(throwable: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, throwable.localizedMessage))
        }
    }

    inner class JengaMpesaStkSubscriber : DisposableSubscriber<MpesaPushResponse>() {

        override fun onComplete() {}

        override fun onNext(t: MpesaPushResponse) {
            mpesaStkLiveData.postValue(Resource(ResourceState.SUCCESS, mpesaPushViewMapper.mapToView(t), null))
        }

        override fun onError(throwable: Throwable) {
            mpesaStkLiveData.postValue(Resource(ResourceState.ERROR, null, throwable.localizedMessage))
        }
    }

}