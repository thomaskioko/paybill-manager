package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.Customer
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.domain.model.mpesa.Transaction
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.model.MpesaPushResponseView


object JengaFactory {


    fun makeJengaToken(): JengaToken {
        return JengaToken("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeJengaTokenView(): JengaTokenView {
        return JengaTokenView("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeMpesaPushResponse(): MpesaPushResponse {
        return MpesaPushResponse(
                "ws_CO_DMZ_215811302_09012019022651831",
                "Success. Request accepted for processing"
        )
    }

    fun makeMpesaPushResponseView(): MpesaPushResponseView {
        return MpesaPushResponseView(
                "ws_CO_DMZ_215811302_09012019022651831",
                "Success. Request accepted for processing"
        )
    }

    fun makeMpesaPushRequest(): MpesaPushRequest {
        return MpesaPushRequest(
                Transaction("10", "ref_2434ds", "Bill Payment", "320320"),
                Customer("0721345321", "KE")
        )
    }

}