package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import javax.inject.Inject

open class MpesaPushResponseMapper @Inject constructor()
    : EntityMapper<MpesaPushResponseEntity, MpesaPushResponse> {

    override fun mapFromEntity(entity: MpesaPushResponseEntity): MpesaPushResponse {
        return MpesaPushResponse(entity.transactionRef, entity.status)
    }

    override fun mapToEntity(domain: MpesaPushResponse): MpesaPushResponseEntity {
        return MpesaPushResponseEntity(domain.transactionRef, domain.status)
    }
}