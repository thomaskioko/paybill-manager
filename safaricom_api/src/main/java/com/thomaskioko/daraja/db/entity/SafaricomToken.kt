package com.thomaskioko.daraja.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.ZoneId


@Entity
data class SafaricomToken(
        @SerializedName("expires_in")
        var expiresIn: String,
        @SerializedName("access_token")
        var accessToken: String
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var expireTime: OffsetDateTime = OffsetDateTime.now(ZoneId.of("Z")).plusSeconds(expiresIn.toLong())

}
