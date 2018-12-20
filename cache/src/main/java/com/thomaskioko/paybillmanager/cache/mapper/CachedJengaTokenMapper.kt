package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedJengaToken
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import javax.inject.Inject

class CachedJengaTokenMapper @Inject constructor() : CacheMapper<CachedJengaToken, JengaTokenEntity> {

    override fun mapFromCached(type: CachedJengaToken): JengaTokenEntity {
        return JengaTokenEntity(type.tokenType, type.issuedAt, type.expiresIn, type.accessToken)
    }

    override fun mapToCached(type: JengaTokenEntity): CachedJengaToken {
        return CachedJengaToken(type.tokenType, type.issuedAt, type.expiresIn, type.accessToken)
    }

}