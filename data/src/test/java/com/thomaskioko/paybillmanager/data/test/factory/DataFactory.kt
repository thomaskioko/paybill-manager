package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.JengaTokenEntity
import com.thomaskioko.paybillmanager.domain.model.JengaToken
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

}