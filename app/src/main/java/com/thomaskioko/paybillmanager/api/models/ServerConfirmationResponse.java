package com.thomaskioko.paybillmanager.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Thomas Kioko
 */
public class ServerConfirmationResponse {

    @SerializedName(value = "response")
    private PaymentConfirmationResponse paymentConfirmationResponse;

    /**
     * @return The paymentConfirmationResponse
     */
    public PaymentConfirmationResponse getPaymentConfirmationResponse() {
        return paymentConfirmationResponse;
    }

    /**
     * @param paymentConfirmationResponse The paymentConfirmationResponse
     */
    public void setPaymentConfirmationResponse(PaymentConfirmationResponse paymentConfirmationResponse) {
        this.paymentConfirmationResponse = paymentConfirmationResponse;
    }
}
