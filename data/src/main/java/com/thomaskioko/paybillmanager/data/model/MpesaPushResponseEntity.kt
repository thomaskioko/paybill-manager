package com.thomaskioko.paybillmanager.data.model

data class MpesaPushResponseEntity(
        val transactionRef: String,
        val status: String
)