package com.thomaskioko.paybillmanager.api.models;

/**
 * @author Thomas Kioko
 */
public class PostPayBillData {

    private String phoneNumber;
    private String totalAmount;
    private String clientName;
    private String clientLocation;

    /**
     * @return The phone number of your client
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return The total amount you are charging the client
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * @return
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * @param clientName
     */
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    /**
     * @return
     */
    public String getClientLocation() {
        return clientLocation;
    }

    /**
     * @param clientLocation
     */
    public void setClientLocation(String clientLocation) {
        this.clientLocation = clientLocation;
    }
}
