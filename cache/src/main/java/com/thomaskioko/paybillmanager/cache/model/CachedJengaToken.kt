package com.thomaskioko.paybillmanager.cache.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jenga_token")
data class CachedJengaToken(
        val tokenType: String,
        val issuedAt: String,
        val expiresIn: String,
        val accessToken: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
