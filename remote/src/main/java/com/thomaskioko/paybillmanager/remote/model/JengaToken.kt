package com.thomaskioko.paybillmanager.remote.model

import com.google.gson.annotations.SerializedName

data class JengaToken(
        @SerializedName("token_type")
        val tokenType: String,
        @SerializedName("issued_at")
        val issuedAt: String,
        @SerializedName("expires_in")
        val expiresIn: String,
        @SerializedName("access_token")
        val accessToken: String
)