package com.thomaskioko.paybillmanager.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.util.UUID;

/**
 * @author Thomas Kioko
 */

public class PayBill extends SugarRecord {

    @Unique
    private String paybillId;
    private String categoryId;
    private String paybillName;
    private String paybillNumber;
    private String paybillAccountNumber;

    /**
     * Default constructor requried by SugarOrm to create the table
     */
    public PayBill() {

    }

    /**
     * Constructor
     *
     * @param categoryId           Category Id
     * @param paybillName          PayBill name
     * @param paybillNumber        PayBill number
     * @param paybillAccountNumber User Account number
     */
    public PayBill(String categoryId, String paybillName, String paybillNumber, String paybillAccountNumber) {
        this.paybillId = UUID.randomUUID().toString();
        this.categoryId = categoryId;
        this.paybillName = paybillName;
        this.paybillNumber = paybillNumber;
        this.paybillAccountNumber = paybillAccountNumber;
    }

    /**
     * Get the PayBill ID
     *
     * @return {@link String }
     */
    public String getPaybillId() {
        return paybillId;
    }

    /**
     * Set the paybill ID
     *
     * @param paybillId PayBill ID
     */
    public void setPaybillId(String paybillId) {
        this.paybillId = paybillId;
    }

    /**
     * Get the Category Id
     *
     * @return {@link String }
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Set the category Id
     *
     * @param categoryId Category Id
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get the paybill name
     *
     * @return {@link String }
     */
    public String getPaybillName() {
        return paybillName;
    }

    /**
     * Set the paybill name
     *
     * @param paybillName PayBill name
     */
    public void setPaybillName(String paybillName) {
        this.paybillName = paybillName;
    }

    /**
     * Get the paybill number
     *
     * @return {@link String }
     */
    public String getPaybillNumber() {
        return paybillNumber;
    }

    /**
     * Set the paybill number
     *
     * @param paybillNumber PayBill number
     */
    public void setPaybillNumber(String paybillNumber) {
        this.paybillNumber = paybillNumber;
    }

    /**
     * Get the account number
     *
     * @return {@link String }
     */
    public String getPaybillAccountNumber() {
        return paybillAccountNumber;
    }

    /**
     * Set the account number
     *
     * @param paybillAccountNumber Account number
     */
    public void setPaybillAccountNumber(String paybillAccountNumber) {
        this.paybillAccountNumber = paybillAccountNumber;
    }
}
