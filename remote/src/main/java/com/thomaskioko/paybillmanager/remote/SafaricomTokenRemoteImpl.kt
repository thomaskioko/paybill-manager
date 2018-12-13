package com.thomaskioko.paybillmanager.remote

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.data.repository.token.TokenRemote
import com.thomaskioko.paybillmanager.remote.mapper.TokenResponseModelMapper
import com.thomaskioko.paybillmanager.remote.service.safaricom.SafaricomTokenService
import io.reactivex.Flowable
import javax.inject.Inject

open class SafaricomTokenRemoteImpl @Inject constructor(
        private val mapper: TokenResponseModelMapper,
        private val serviceSafaricom: SafaricomTokenService
) : TokenRemote {
    override fun getSafaricomToken(): Flowable<SafaricomTokenEntity> {
        return serviceSafaricom.getAccessToken().map {
            mapper.mapFromModel(it)
        }
    }
}