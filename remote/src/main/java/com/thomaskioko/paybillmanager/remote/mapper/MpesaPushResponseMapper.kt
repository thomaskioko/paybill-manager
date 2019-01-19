package com.thomaskioko.paybillmanager.remote.mapper


import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.remote.model.MpesaPushResponse
import javax.inject.Inject

open class MpesaPushResponseMapper @Inject constructor() :
        ModelMapper<MpesaPushResponse, MpesaPushResponseEntity> {

    override fun mapFromModel(model: MpesaPushResponse): MpesaPushResponseEntity {
        return MpesaPushResponseEntity(model.transactionReference, model.statusMessage)
    }

}