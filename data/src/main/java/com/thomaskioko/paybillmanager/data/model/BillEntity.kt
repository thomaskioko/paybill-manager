package com.thomaskioko.paybillmanager.data.model


data class BillEntity(
        var billId: String,
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: String,
        var reminderDate: Long
)