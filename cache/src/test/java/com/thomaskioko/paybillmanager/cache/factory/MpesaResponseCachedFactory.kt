package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedMpesaPushResponse
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity

object MpesaResponseCachedFactory {

    fun makeCacehedMpesaPushResponse(): CachedMpesaPushResponse {
        return CachedMpesaPushResponse(
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
}