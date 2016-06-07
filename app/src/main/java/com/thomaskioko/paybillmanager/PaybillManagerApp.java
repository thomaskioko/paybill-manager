package com.thomaskioko.paybillmanager;

import com.orm.SugarApp;
import com.orm.SugarContext;
import com.thomaskioko.paybillmanager.models.PaybillCategory;

/**
 * Application class.
 *
 * @author Thomas Kioko
 */

public class PaybillManagerApp extends SugarApp {


    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

    }

}
