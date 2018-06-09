package com.thomaskioko.daraja.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
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