package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.Config


object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(-1, DataFactory.randomLong())
    }

}