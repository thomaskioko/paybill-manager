package com.thomaskioko.paybillmanager.util;

import com.thomaskioko.paybillmanager.BuildConfig;

/**
 * @author Thomas Kioko
 */
public class ApplicationConstants {

    /**
     * Set to true to Enable Debugging in the API false to disable. This should be false when
     * releasing the app.
     */
    public static final boolean DEBUG = BuildConfig.DEBUG;

    /**
     * API Endpoint
     */
    public static final String END_POINT = BuildConfig.API_URL + "/project-mulla/api/v1/payment/";
    /**
     * Connection timeout duration
     */
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * Connection Read timeout duration
     */
    public static final int READ_TIMEOUT = 60 * 1000;
    /**
     * Connection write timeout duration
     */
    public static final int WRITE_TIMEOUT = 60 * 1000;
    /**
     * Tag used by {@link android.support.v4.content.LocalBroadcastManager}
     */
    public static final String RELOAD_PAY_BILLS = "reloadPayBillsTag";

}
