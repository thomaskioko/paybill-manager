package com.thomaskioko.paybillmanager.models;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * @author Thomas Kioko
 */

public class PayBillCategory extends SugarRecord {

    @Unique
    private String categoryId;
    private String categoryName;
    private String categoryDrawableId;


    public PayBillCategory() {

    }

    /**
     * Constructor
     *
     * @param categoryId         Primary key
     * @param categoryName       Name of the Category
     * @param categoryDrawableId Path of the drawable image
     */
    public PayBillCategory(String categoryId, String categoryName, String categoryDrawableId) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.categoryDrawableId = categoryDrawableId;
    }

    /**
     * Get the category Id
     *
     * @return {@link String }
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * Set the category Id
     *
     * @param categoryId Category ID
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Get the category name
     *
     * @return {@link String }
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set the category name
     *
     * @param categoryName Name of the category
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Get the drawable ID
     *
     * @return {@link String }
     */
    public String getCategoryDrawableId() {
        return categoryDrawableId;
    }

    /**
     * Set the drawable ID
     *
     * @param categoryDrawableId Category drawable ID
     */
    public void setCategoryDrawableId(String categoryDrawableId) {
        this.categoryDrawableId = categoryDrawableId;
    }
}
