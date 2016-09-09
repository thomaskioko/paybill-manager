package com.thomaskioko.paybillmanager.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Thomas Kioko
 */
public class PaymentResponse {

    @SerializedName(value = "return_code")
    private String returnCode;
    @SerializedName(value = "status_code")
    private Integer statusCode;
    private String message;
    @SerializedName(value = "transaction_id")
    private String transactionId;
    private String description;
    @SerializedName(value = "cust_msg")
    private String customMessage;
    @SerializedName(value = "reference_id")
    private String referenceId;
    @SerializedName(value = "merchant_transaction_id")
    private String merchantTransactionId;
    @SerializedName(value = "amount_in_double_float")
    private String amountInDoubleFloat;
    @SerializedName(value = "client_phone_number")
    private String clientPhoneNumber;
    @SerializedName(value = "extra_payload")
    private ExtraPayload extraPayload;
    @SerializedName(value = "time_stamp")
    private String timeStamp;

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
     * @return The customMessage
     */
    public String getCustomMessage() {
        return customMessage;
    }

    /**
     * @param customMessage The cust_msg
     */
    public void setCustomMessage(String customMessage) {
        this.customMessage = customMessage;
    }

    /**
     * @return The referenceId
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * @param referenceId The reference_id
     */
    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    /**
     * @return The merchantTransactionId
     */
    public String getMerchantTransactionId() {
        return merchantTransactionId;
    }

    /**
     * @param merchantTransactionId The merchant_transaction_id
     */
    public void setMerchantTransactionId(String merchantTransactionId) {
        this.merchantTransactionId = merchantTransactionId;
    }

    /**
     * @return The amountInDoubleFloat
     */
    public String getAmountInDoubleFloat() {
        return amountInDoubleFloat;
    }

    /**
     * @param amountInDoubleFloat The amount_in_double_float
     */
    public void setAmountInDoubleFloat(String amountInDoubleFloat) {
        this.amountInDoubleFloat = amountInDoubleFloat;
    }

    /**
     * @return The clientPhoneNumber
     */
    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    /**
     * @param clientPhoneNumber The client_phone_number
     */
    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }

    /**
     * @return The extraPayload
     */
    public ExtraPayload getExtraPayload() {
        return extraPayload;
    }

    /**
     * @param extraPayload The extra_payload
     */
    public void setExtraPayload(ExtraPayload extraPayload) {
        this.extraPayload = extraPayload;
    }

    /**
     * @return The timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp The time_stamp
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
