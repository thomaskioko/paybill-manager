package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedToken
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import javax.inject.Inject

class CachedTokenMapper @Inject constructor() : CacheMapper<CachedToken, SafaricomTokenEntity> {

    override fun mapFromCached(type: CachedToken): SafaricomTokenEntity {
        return SafaricomTokenEntity(type.id, type.expiresIn, type.accessToken)
    }

    override fun mapToCached(type: SafaricomTokenEntity): CachedToken {
        return CachedToken(type.id, type.expiresIn, type.accessToken)
    }

}