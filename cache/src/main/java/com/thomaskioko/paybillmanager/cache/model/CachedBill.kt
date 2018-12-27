package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "bill")
data class CachedBill(
        @PrimaryKey
        var id: String,
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: String,
        var reminderDate: Long
)