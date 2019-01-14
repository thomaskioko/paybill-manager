package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedMpesaPushResponse
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import javax.inject.Inject

class CachedMpesaPushResponseMapper @Inject constructor()
    : CacheMapper<CachedMpesaPushResponse, MpesaPushResponseEntity> {

    override fun mapFromCached(type: CachedMpesaPushResponse): MpesaPushResponseEntity {
        return MpesaPushResponseEntity(type.transactionReference, type.statusMessage)
    }

    override fun mapToCached(type: MpesaPushResponseEntity): CachedMpesaPushResponse {
        return CachedMpesaPushResponse(type.transactionReference, type.statusMessage)
    }

}