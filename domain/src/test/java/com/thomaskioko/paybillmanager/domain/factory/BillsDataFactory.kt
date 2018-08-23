package com.thomaskioko.paybillmanager.domain.factory

import com.thomaskioko.paybillmanager.domain.model.Bill
import java.util.*

object BillsDataFactory {

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
        return Bill(randomInt(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomInt(),
                randomLong())
    }

    fun makeProjectList(count: Int): List<Bill> {
        val projects = mutableListOf<Bill>()
        repeat(count) {
            projects.add(makeBill())
        }
        return projects
    }
}