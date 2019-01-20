package com.thomaskioko.paybillmanager.domain.model


data class Bill(
        val billId: String,
        val billName: String,
        val paybillNumber: String,
        val accountNumber: String,
        val amount: String,
        val categoryId: String,
        val reminderDate: Long
)