package com.thomaskioko.paybillmanager.remote.model

import com.google.gson.annotations.SerializedName

data class MpesaPushResponse(
        @SerializedName("transactionRef")
        val transactionReference: String,
        @SerializedName("status")
        val statusMessage: String
)