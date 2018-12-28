package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.model.BillView


object BillsFactory {

    fun makeStaticBill(): Bill {
        return Bill("12", "Zuku", "320320",
                "Zuku123443", "4900", "2",
                156223772
        )
    }

    fun makeBill(): Bill {
        return Bill(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomLong())
    }


    fun makeBillList(count: Int): List<Bill> {
        val billEntities = mutableListOf<Bill>()
        repeat(count) {
            billEntities.add(makeBill())
        }
        return billEntities
    }

    fun makeBillView(): BillView {
        return BillView(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomLong())
    }

    fun makeBillViewList(count: Int): List<BillView> {
        val billEntities = mutableListOf<BillView>()
        repeat(count) {
            billEntities.add(makeBillView())
        }
        return billEntities
    }


}