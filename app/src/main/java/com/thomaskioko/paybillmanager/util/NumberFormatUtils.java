package com.thomaskioko.paybillmanager.util;

import java.text.DecimalFormat;

/**
 * This class is used to handle number formatting.
 *
 * @author Thomas Kioko
 */
public class NumberFormatUtils {

    /**
     * Format the amount to a standard currency format e.g 1,200.00
     *
     * @param amount Amount
     * @return Formatted amount
     */
    public static String formatAmount(String amount) {

        DecimalFormat formatter = new DecimalFormat("###,###,###.00");
        Double price = Double.parseDouble(amount.replace(",", ""));
        return formatter.format(price);
    }
}
