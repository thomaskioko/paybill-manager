package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.presentation.model.MpesaPushResponseView
import javax.inject.Inject

open class MpesaPushViewMapper @Inject constructor() : Mapper<MpesaPushResponseView, MpesaPushResponse> {

    override fun mapToView(type: MpesaPushResponse): MpesaPushResponseView {
        return MpesaPushResponseView(type.transactionReference, type.statusMessage)
    }
}