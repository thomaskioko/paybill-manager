package com.thomaskioko.paybillmanager.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Thomas Kioko
 */
public class PaymentConfirmationResponse {
    @SerializedName(value = "return_code")
    private String returnCode;
    @SerializedName(value = "status_code")
    private Integer statusCode;
    private String message;
    private String description;
    @SerializedName(value = "trx_id")
    private String transactionId;

    /**
     * @return The returnCode
     */
    public String getReturnCode() {
        return returnCode;
    }

    /**
     * @param returnCode The return_code
     */
    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    /**
     * @return The statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode The status_code
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * @param transactionId The trx_id
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }
}
