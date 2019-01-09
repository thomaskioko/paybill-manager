package com.thomaskioko.paybillmanager.domain.model

data class MpesaPushResponse(
        val transactionRef: String,
        val status: String
)