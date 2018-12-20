package com.thomaskioko.paybillmanager.remote.factory

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.remote.model.JengaToken
import java.util.*

object TestDataFactory {

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

}