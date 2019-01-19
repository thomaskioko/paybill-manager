package com.thomaskioko.paybillmanager.data.model

import com.thomaskioko.paybillmanager.domain.model.mpesa.Customer
import com.thomaskioko.paybillmanager.domain.model.mpesa.Transaction

data class MpesaPushRequestEntity(
        val transaction: Transaction,
        val customer: Customer
)
