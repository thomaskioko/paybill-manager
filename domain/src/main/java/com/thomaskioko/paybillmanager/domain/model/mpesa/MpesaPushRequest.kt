package com.thomaskioko.paybillmanager.domain.model.mpesa

data class MpesaPushRequest(
        val transaction: Transaction,
        val customer: Customer
)
