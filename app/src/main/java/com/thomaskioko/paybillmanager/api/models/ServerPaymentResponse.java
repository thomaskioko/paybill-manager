package com.thomaskioko.paybillmanager.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Thomas Kioko
 */
public class ServerPaymentResponse {

    @SerializedName(value = "response")
    private PaymentResponse paymentResponse;

    /**
     * @return The paymentResponse
     */
    public PaymentResponse getPaymentResponse() {
        return paymentResponse;
    }

    /**
     * @param paymentResponse The paymentResponse
     */
    public void setPaymentResponse(PaymentResponse paymentResponse) {
        this.paymentResponse = paymentResponse;
    }
}
