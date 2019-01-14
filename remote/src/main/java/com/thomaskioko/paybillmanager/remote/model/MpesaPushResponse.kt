package com.thomaskioko.paybillmanager.remote.model

import com.google.gson.annotations.SerializedName

data class MpesaPushResponse(
        @SerializedName("transactionReference")
        val transactionReference: String,
        @SerializedName("statusMessage")
        val statusMessage: String
)