package com.thomaskioko.paybillmanager.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author Thomas Kioko
 */
public class PaymentStatusResponse {

    @SerializedName(value = "return_code")
    private String returnCode;
    @SerializedName(value = "status_code")
    private Integer statusCode;
    private String message;
    @SerializedName(value = "transaction_status")
    private String trxStatus;
    @SerializedName(value = "trx_id")
    private String transactionId;
    private String msisdn;
    private String amount;
    @SerializedName(value = "mpesa_trx_id")
    private String mpesaTransactionId;

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
     * @return The trxStatus
     */
    public String getTrxStatus() {
        return trxStatus;
    }

    /**
     * @param trxStatus The trx_status
     */
    public void setTrxStatus(String trxStatus) {
        this.trxStatus = trxStatus;
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
     * @return The msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @param msisdn The msisdn
     */
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    /**
     * @return The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * @param amount The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * @return The mpesaTransactionId
     */
    public String getMpesaTransactionId() {
        return mpesaTransactionId;
    }

    /**
     * @param mpesaTransactionId The mpesa_trx_id
     */
    public void setMpesaTransactionId(String mpesaTransactionId) {
        this.mpesaTransactionId = mpesaTransactionId;
    }

}
