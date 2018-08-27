package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.remote.mapper.TokenResponseModelMapper
import com.thomaskioko.paybillmanager.remote.service.TokenService
import io.reactivex.Flowable
import javax.inject.Inject

open class TokenRemoteImpl @Inject constructor(
        private val mapper: TokenResponseModelMapper,
        private val service: TokenService
) : TokenRemote {
    override fun getSafaricomToken(): Flowable<SafaricomTokenEntity> {
        return service.getAccessToken().map {
            mapper.mapFromModel(it)
        }
    }
}