package com.thomaskioko.paybillmanager.domain.model

data class MpesaPushResponse(
        val transactionReference: String,
        val statusMessage: String
)