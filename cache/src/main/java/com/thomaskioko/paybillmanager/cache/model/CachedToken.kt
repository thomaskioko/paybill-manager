package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "token")
data class CachedToken(
        @PrimaryKey
        var id: Int,
        var expiresIn: Long,
        var accessToken: String
)