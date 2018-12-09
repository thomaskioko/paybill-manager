package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedToken
import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity


object TokenCachedFactory {

    fun makeCachedToken(): CachedToken {
        return CachedToken(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
    }


    fun makeSafaricomTokenEntity(): SafaricomTokenEntity {
        return SafaricomTokenEntity(DataFactory.randomInt(), DataFactory.randomLong(), DataFactory.randomUuid())
    }

}