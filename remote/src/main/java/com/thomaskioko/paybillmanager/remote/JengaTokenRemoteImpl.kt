package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenRemote
import com.thomaskioko.paybillmanager.remote.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.remote.service.JengaService
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaTokenRemoteImpl @Inject constructor(
        private val mapper: JengaTokenMapper,
        private val service: JengaService
) : JengaTokenRemote {

    override fun getJengaToken(): Flowable<JengaTokenEntity> {
        return service.getAccessToken(BuildConfig.JENGA_USERNAME, BuildConfig.JENGA_PASSWORD).map {
            mapper.mapFromModel(it)
        }
    }
}