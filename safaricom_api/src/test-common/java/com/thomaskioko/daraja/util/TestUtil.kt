package com.thomaskioko.daraja.util

import com.thomaskioko.daraja.model.SafaricomPushRequest
import com.thomaskioko.daraja.db.entity.PushRequestResponse
import com.thomaskioko.daraja.db.entity.SafaricomToken
import com.thomaskioko.daraja.util.AppConstants.Companion.CALLBACKURL
import com.thomaskioko.daraja.util.AppConstants.Companion.TEST_BUSINESS_SHORT_CODE
import com.thomaskioko.daraja.util.AppConstants.Companion.TEST_PARTYB
import com.thomaskioko.daraja.util.AppConstants.Companion.TEST_PASSKEY
import com.thomaskioko.daraja.util.AppConstants.Companion.TRANSACTION_TYPE

object TestUtil {


    fun createToken() = SafaricomToken("3599", "jK13flUwxNhIsYPzvNAJwslDnruQ")

    fun createToken(token: String) = SafaricomToken("3599", token)


    fun createSafaricomPushRequest() = SafaricomPushRequest(
            12, TEST_BUSINESS_SHORT_CODE, TEST_PASSKEY, "1528378513",
            TRANSACTION_TYPE, "30", TEST_PARTYB, TEST_PARTYB, "0721000000",
            CALLBACKURL, "123-Test-Paybill", "Paybill"
    )

    fun createPushRequest() = PushRequestResponse(
            "25745-1565482-1",
            "0",
            "Success. Request accepted for processing",
            "ws_CO_DMZ_37595383_07062018174946163",
            "Success. Request accepted for processing"
    )

    fun createPushRequest(checkoutRequestID: String) = PushRequestResponse(
            "25745-1565482-1",
            "0",
            "Success. Request accepted for processing",
            checkoutRequestID,
            "Success. Request accepted for processing"
    )


    fun createSafaricomPushRequest(id: Int, accountReference: String) = SafaricomPushRequest(
            id, TEST_BUSINESS_SHORT_CODE, TEST_PASSKEY, "1528378513",
            TRANSACTION_TYPE, "30", TEST_PARTYB, TEST_PARTYB, "0721000000",
            CALLBACKURL, accountReference, "Paybill"
    )
}