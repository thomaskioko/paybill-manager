package com.thomaskioko.daraja.repository.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class SafaricomToken(
        @SerializedName("expires_in")
        var expiresIn: String,
        @SerializedName("access_token")
        var accessToken: String,
        var expireTime: Long
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}