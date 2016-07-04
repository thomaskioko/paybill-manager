package com.thomaskioko.paybillmanager.api.models;

/**
 * @author Thomas Kioko
 */
public class ServerStatusResponse {

    private PaymentStatusResponse response;

    /**
     * @return The response
     */
    public PaymentStatusResponse getResponse() {
        return response;
    }

    /**
     * @param response The response
     */
    public void setResponse(PaymentStatusResponse response) {
        this.response = response;
    }
}
