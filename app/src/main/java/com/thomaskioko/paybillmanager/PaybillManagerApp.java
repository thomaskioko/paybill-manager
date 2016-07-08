package com.thomaskioko.paybillmanager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.thomaskioko.paybillmanager.models.PayBillCategory;

import java.lang.reflect.Field;

/**
 * Application class.
 *
 * @author Thomas Kioko
 */

public class PaybillManagerApp extends SugarApp {

    /**
     * @param context Application context
     * @return Paybillmanager class instance
     */
    public static PaybillManagerApp from(Context context) {
        return (PaybillManagerApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

        /**
         * Create the paybill categories. At the moment this is hard coded. At the moment, we have the
         * following categories.
         *
         * 1. Utilities: KPLC, Nairobi Water
         * 2. Entertainment: Zuku, DsTV
         * 3. Internet: Sasahost
         * 4. Others
         */
        PayBillCategory payBillCategoryUtilities = new PayBillCategory("1", "Utilities",
                String.valueOf(getResId("ic_utilities.png", Drawable.class)));
        payBillCategoryUtilities.save();
        PayBillCategory payBillCategoryEntertainment = new PayBillCategory("2", "Entertainment",
                String.valueOf(getResId("ic_entertainment.png", Drawable.class)));
        payBillCategoryEntertainment.save();
        PayBillCategory payBillCategoryInternet = new PayBillCategory("3", "Internet",
                String.valueOf(getResId("ic_internet.png", Drawable.class)));
        payBillCategoryInternet.save();
        PayBillCategory payBillCategoryOthers = new PayBillCategory("4", "Others",
                String.valueOf(getResId("ic_others.png", Drawable.class)));
        payBillCategoryOthers.save();

    }

    private static int getResId(String resName, Class<?> c) {
        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}
