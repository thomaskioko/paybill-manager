package com.thomaskioko.paybillmanager.presentation.model

class BillView(
        var billId: String,
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: String,
        var reminderDate: Long
)