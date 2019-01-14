package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaRemote
import com.thomaskioko.paybillmanager.remote.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.remote.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.remote.service.JengaService
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaRemoteImpl @Inject constructor(
        private val mapper: JengaTokenMapper,
        private val responseMapper: MpesaPushResponseMapper,
        private val service: JengaService
) : JengaRemote {

    override fun getJengaToken(): Flowable<JengaTokenEntity> {
        return service.getAccessToken(
                BuildConfig.JENGA_API_KEY,
                BuildConfig.JENGA_USERNAME,
                BuildConfig.JENGA_PASSWORD).map {
            mapper.mapFromModel(it)
        }
    }

    override fun getMpesaPushResponse(bearerToken: String, signature: String)
            : Flowable<MpesaPushResponseEntity> {
       return service.getMpesaStkPush(bearerToken, signature)
               .map {
                   responseMapper.mapFromModel(it)
               }
    }
}