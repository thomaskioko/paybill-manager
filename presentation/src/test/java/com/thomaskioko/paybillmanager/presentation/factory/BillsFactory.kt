package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.Bill


object BillsFactory {

    fun makeBill(): Bill {
        return Bill(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }


    fun makeBillEntityList(count: Int): List<Bill> {
        val billEntities = mutableListOf<Bill>()
        repeat(count) {
            billEntities.add(makeBill())
        }
        return billEntities
    }


}