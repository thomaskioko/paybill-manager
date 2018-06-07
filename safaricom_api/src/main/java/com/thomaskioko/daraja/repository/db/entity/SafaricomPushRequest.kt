package com.thomaskioko.daraja.repository.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.google.gson.annotations.SerializedName

@Entity
data class SafaricomPushRequest(
        @PrimaryKey(autoGenerate = true)
        @JsonIgnoreProperties(ignoreUnknown = true)
        var id: Int,
        @SerializedName("BusinessShortCode")
        var businessShortCode: String,
        @SerializedName("Password")
        var password: String,
        @SerializedName("Timestamp")
        var timestamp: String,
        @SerializedName("TransactionType")
        var transactionType: String,
        @SerializedName("Amount")
        var amount: String,
        @SerializedName("PartyA")
        var partyA: String,
        @SerializedName("PartyB")
        var partyB: String,
        @SerializedName("PhoneNumber")
        var phoneNumber: String,
        @SerializedName("CallBackURL")
        var callBackURL: String,
        @SerializedName("AccountReference")
        var accountReference: String,
        @SerializedName("TransactionDesc")
        var transactionDesc: String
)