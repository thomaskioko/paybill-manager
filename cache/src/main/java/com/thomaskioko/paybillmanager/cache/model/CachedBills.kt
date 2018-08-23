package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bills")
data class CachedBills(
        var billName: String,
        var paybillNumber: String,
        var accountNumber: String,
        var amount: String,
        var categoryId: Int,
        var reminderDate: Long?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}