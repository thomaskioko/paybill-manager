package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory.randomInt
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory.randomLong
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.domain.model.Bill

object BillsDataFactory {

    fun makeBill(): Bill {
        return Bill(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(),
                randomLong())
    }

    fun makeUpdateBill(name: String): Bill {
        return Bill(randomUuid(), name, randomUuid(),
                randomUuid(), randomUuid(), randomUuid(),
                randomLong())
    }


    fun makeBillEntity(): BillEntity {
        return BillEntity(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomUuid(),
                randomLong())
    }

    fun makeBillList(count: Int): List<Bill> {
        val bills = mutableListOf<Bill>()
        repeat(count) {
            bills.add(makeBill())
        }
        return bills
    }

    fun makeBillEntityList(count: Int): List<BillEntity> {
        val billEntities = mutableListOf<BillEntity>()
        repeat(count) {
            billEntities.add(makeBillEntity())
        }
        return billEntities
    }

}