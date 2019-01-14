package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mpesa_response")
data class CachedMpesaPushResponse(
        val transactionReference: String,
        val statusMessage: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}