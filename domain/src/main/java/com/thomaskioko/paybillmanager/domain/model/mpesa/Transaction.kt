package com.thomaskioko.paybillmanager.domain.model.mpesa

data class Transaction(
        val amount: String,
        val reference: String,
        val description: String,
        val businessNumber: String
)
