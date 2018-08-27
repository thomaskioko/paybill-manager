package com.thomaskioko.paybillmanager.remote.factory

import com.thomaskioko.paybillmanager.data.model.SafaricomTokenEntity
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.SafaricomToken
import com.thomaskioko.paybillmanager.remote.model.SafaricomTokenModel
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

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

    fun makeBill(): Bill {
        return Bill(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomInt(),
                randomLong())
    }

    fun makeSafaricomToken(): SafaricomToken {
        return SafaricomToken(randomInt(), randomUuid(), randomUuid())
    }

    fun makeSafaricomEntity(): SafaricomTokenEntity {
        return SafaricomTokenEntity(randomInt(), randomLong(), randomUuid())
    }

    fun makeSafaricomTokenModel(): SafaricomTokenModel {
        return SafaricomTokenModel(randomLong().toString(), randomUuid())
    }

}