package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedJengaToken
import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity


object TokenCachedFactory {

    fun makeCachedJengaToken(): CachedJengaToken {
        return CachedJengaToken("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

    fun makeJengaTokenEntity(): JengaTokenEntity {
        return JengaTokenEntity("bearer", "1544517293525", "3599", "EJ4CSPoMBIYAj8KLUp45d5CUflvm9lz")
    }

}