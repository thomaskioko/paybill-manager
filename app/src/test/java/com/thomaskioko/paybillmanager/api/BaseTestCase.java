package com.thomaskioko.paybillmanager.api;

import com.thomaskioko.paybillmanager.util.ApplicationConstants;

import org.junit.BeforeClass;

/**
 * @author Thomas Kioko
 */
public abstract class BaseTestCase {

    private final static ApiClient mTmdbApiClient = new ApiClient();

    @BeforeClass
    public static void setUpOnce() {
        mTmdbApiClient.setIsDebug(ApplicationConstants.DEBUG);
    }

    /**
     * @return {@link ApiClient} instance.
     */
    protected final ApiClient getApiClient() {
        return mTmdbApiClient;
    }
}
