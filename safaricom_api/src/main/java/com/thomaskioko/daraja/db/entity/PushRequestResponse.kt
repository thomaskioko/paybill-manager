package com.thomaskioko.daraja.db.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PushRequestResponse(
        @PrimaryKey
        @NonNull
        @SerializedName("MerchantRequestID")
        val merchantRequestID: String,
        @SerializedName("ResponseCode")
        val responseCode: String,
        @SerializedName("CustomerMessage")
        val customerMessage: String,
        @SerializedName("CheckoutRequestID")
        val checkoutRequestID: String,
        @SerializedName("ResponseDescription")
        val responseDescription: String
)
