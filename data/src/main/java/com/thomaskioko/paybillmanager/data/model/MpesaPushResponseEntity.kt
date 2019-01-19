package com.thomaskioko.paybillmanager.data.model

data class MpesaPushResponseEntity(
        val transactionReference: String,
        val statusMessage: String
)