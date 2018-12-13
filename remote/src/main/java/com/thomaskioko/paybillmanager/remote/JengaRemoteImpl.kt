package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaRemote
import com.thomaskioko.paybillmanager.remote.mapper.JengaTokenMapper
import com.thomaskioko.paybillmanager.remote.service.jenga.JengaService
import io.reactivex.Flowable
import javax.inject.Inject

open class JengaRemoteImpl @Inject constructor(
        private val mapper: JengaTokenMapper,
        private val service: JengaService
) : JengaRemote {

    override fun getJengaToken(username: String, password: String): Flowable<JengaTokenEntity> {
        return service.getAccessToken(username, password).map {
            mapper.mapFromModel(it)
        }
    }
}