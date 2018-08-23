package com.thomaskioko.paybillmanager.data.model


class BillEntity(
        var billId: Int,
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: Int,
        var reminderDate: Long?
)