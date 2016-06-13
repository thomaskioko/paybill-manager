package com.thomaskioko.paybillmanager;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.thomaskioko.paybillmanager.models.PaybillCategory;

import java.lang.reflect.Field;

/**
 * Application class.
 *
 * @author Thomas Kioko
 */

public class PaybillManagerApp extends SugarApp{

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
        PaybillCategory paybillCategoryUtilities = new PaybillCategory("1", "Utilities",
                String.valueOf(getResId("ic_utilities.png", Drawable.class)));
        paybillCategoryUtilities.save();
        PaybillCategory paybillCategoryEntertainment = new PaybillCategory("2", "Entertainment",
                String.valueOf(getResId("ic_entertainment.png", Drawable.class)));
        paybillCategoryEntertainment.save();
        PaybillCategory paybillCategoryInternet = new PaybillCategory("3", "Internet",
                String.valueOf(getResId("ic_internet.png", Drawable.class)));
        paybillCategoryInternet.save();
        PaybillCategory paybillCategoryOthers = new PaybillCategory("4", "Others",
                String.valueOf(getResId("ic_others.png", Drawable.class)));
        paybillCategoryOthers.save();

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
