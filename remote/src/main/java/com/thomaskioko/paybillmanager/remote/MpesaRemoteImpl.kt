package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRemote
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.remote.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.remote.service.JengaService
import io.reactivex.Flowable
import javax.inject.Inject

open class MpesaRemoteImpl @Inject constructor(
        private val responseMapper: MpesaPushResponseMapper,
        private val service: JengaService
) : MpesaPushRemote {

    override fun getMpesaStkPushRequest(bearerToken: String, signaturePayload: String)
            : Flowable<MpesaPushResponseEntity> {
        return service.getMpesaStkPush(bearerToken, signaturePayload)
                .map {
                    responseMapper.mapFromModel(it)
                }
    }
}