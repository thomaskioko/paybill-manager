package com.thomaskioko.paybillmanager.mobile.factory

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.factory.TestDataFactory.randomInt
import com.thomaskioko.paybillmanager.mobile.factory.TestDataFactory.randomLong
import com.thomaskioko.paybillmanager.mobile.factory.TestDataFactory.randomUuid
import com.thomaskioko.paybillmanager.presentation.model.BillView


object BillsDataFactory {

    fun makeStaticBill(): Bill {
        return Bill("12", "Zuku", "320320",
                "Zuku123443", "4900", 2,
                156223772
        )
    }

    fun makeBill(): Bill {
        return Bill(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), "2400", randomInt(),
                randomLong())
    }


    fun makeBillList(count: Int): List<Bill> {
        val billEntities = mutableListOf<Bill>()
        repeat(count) {
            billEntities.add(makeBill())
        }
        return billEntities
    }

    fun makeBillView(): BillView {
        return BillView(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), "2400", randomInt(),
                randomLong())
    }

    fun makeBillViewList(count: Int): List<BillView> {
        val billEntities = mutableListOf<BillView>()
        repeat(count) {
            billEntities.add(makeBillView())
        }
        return billEntities
    }


}