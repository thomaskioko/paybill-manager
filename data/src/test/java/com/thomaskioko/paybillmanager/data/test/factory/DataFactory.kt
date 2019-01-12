package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.Customer
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.domain.model.mpesa.Transaction
import java.util.*

object DataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomInt(): Int {
        return Math.random().toInt()
    }

    fun randomLong(): Long {
        return Math.random().toLong()
    }

    fun makeJengaToken(): JengaToken {
        return JengaToken("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeJengaTokenEntity(): JengaTokenEntity {
        return JengaTokenEntity("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeMpesaPushResponse(): MpesaPushResponse {
        return MpesaPushResponse(
                "ws_CO_DMZ_215811302_09012019022651831",
                "Success. Request accepted for processing"
        )
    }

    fun makeMpesaPushResponseEntity(): MpesaPushResponseEntity {
        return MpesaPushResponseEntity(
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

    fun makeMpesaPushRequestEntity(): MpesaPushRequestEntity {
        return MpesaPushRequestEntity(
                Transaction("10", "ref_2434ds", "Bill Payment", "320320"),
                Customer("0721345321", "KE")
        )
    }

}