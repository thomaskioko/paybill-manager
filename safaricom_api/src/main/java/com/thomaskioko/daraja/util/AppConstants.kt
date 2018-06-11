package com.thomaskioko.daraja.util

open class AppConstants {

    companion object {

        /**
         * Number of threads allowed for an executor
         */
        const val MAXIMUM_THREADS = 3

        /**
         * Connection timeout duration
         */
        const val CONNECT_TIMEOUT = 60 * 1000
        /**
         * Connection Read timeout duration
         */
        const val READ_TIMEOUT: Long = 60 * 1000
        /**
         * Connection write timeout duration
         */
        const val WRITE_TIMEOUT: Long = 60 * 1000


        /**
         * Safaricom Base URL
         */
        const val SAFARICOM_BASE_URL = "https://sandbox.safaricom.co.ke/"
        /**
         * Safaricom token Request URL
         */
        const val TOKEN_URL = "https://sandbox.safaricom.co.ke/oauth/v1/"

        /**
         * SafaricomPushRequest Properties
         */
        const val TEST_BUSINESS_SHORT_CODE = "174379"
        const val TEST_PASSKEY = "bfb279f9aa9bdbcf158e97dd71a467cd2e0c893059b10f78e6b72ada1ed2c919"
        const val TEST_PARTYB = "174379"
        const val TRANSACTION_TYPE = "CustomerPayBillOnline"
        /**
         * Sent the device token so we can send notifications back to the user.
         */
        const val CALLBACKURL = "https://yourapi/firebaseToken="
    }
}
