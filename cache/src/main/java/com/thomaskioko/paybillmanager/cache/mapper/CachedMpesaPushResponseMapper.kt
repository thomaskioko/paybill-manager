package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedMpesaPushResponse
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import javax.inject.Inject

class CachedMpesaPushResponseMapper @Inject constructor()
    : CacheMapper<CachedMpesaPushResponse, MpesaPushResponseEntity> {

    override fun mapFromCached(type: CachedMpesaPushResponse): MpesaPushResponseEntity {
        return MpesaPushResponseEntity(type.transactionRef, type.status)
    }

    override fun mapToCached(type: MpesaPushResponseEntity): CachedMpesaPushResponse {
        return CachedMpesaPushResponse(type.transactionRef, type.status)
    }

}