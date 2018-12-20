package com.thomaskioko.paybillmanager.mobile.factory

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

    fun randomString(): String {
        return UUID.randomUUID().toString()
    }

}