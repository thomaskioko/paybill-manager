package com.thomaskioko.paybillmanager.remote.model


import com.google.gson.annotations.SerializedName


data class SafaricomTokenModel(
        @SerializedName("expires_in")
        var expiresIn: String,
        @SerializedName("access_token")
        var accessToken: String
)