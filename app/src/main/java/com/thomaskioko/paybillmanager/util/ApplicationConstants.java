package com.thomaskioko.paybillmanager.util;

import android.support.v4.content.LocalBroadcastManager;

/**
 * @author Thomas Kioko
 */
public class ApplicationConstants {
    /**
     * Set to true to Enable Debugging in the API false to disable. This should be false when
     * releasing the app.
     */
    public static final boolean DEBUG = true;
    /**
     * Base API Endpoint.
     * In this case I have setup the endpoint localy just for testing purposes.
     */
    public static final String END_POINT = "http://localhost:8080/api/v1/payment/";
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
     * Tag used by {@link LocalBroadcastManager}
     */
    public static final String RELOAD_PAY_BILLS = "reloadPayBillsTag";

}
