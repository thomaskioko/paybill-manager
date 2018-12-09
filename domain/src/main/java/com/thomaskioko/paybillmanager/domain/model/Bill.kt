package com.thomaskioko.paybillmanager.domain.model


data class Bill(
        var billId: String,
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: Int,
        var reminderDate: Long
)