package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.Config


object ConfigDataFactory {

    fun makeCachedConfig(): Config {
        return Config(DataFactory.randomInt(), DataFactory.randomLong())
    }

}