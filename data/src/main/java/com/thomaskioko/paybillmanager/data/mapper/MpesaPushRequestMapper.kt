package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import javax.inject.Inject

open class MpesaPushRequestMapper @Inject constructor() : EntityMapper<MpesaPushRequestEntity, MpesaPushRequest> {

    override fun mapFromEntity(entity: MpesaPushRequestEntity): MpesaPushRequest {
        return MpesaPushRequest(entity.transaction, entity.customer)
    }

    override fun mapToEntity(domain: MpesaPushRequest): MpesaPushRequestEntity {
        return MpesaPushRequestEntity(domain.transaction, domain.customer)
    }
}