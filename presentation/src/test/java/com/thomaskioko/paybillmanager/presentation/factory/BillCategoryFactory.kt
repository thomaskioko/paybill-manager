package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.presentation.model.BillCategoryView
import com.thomaskioko.paybillmanager.presentation.model.BillView

object BillCategoryFactory {


    fun makeBillCategory(): BillCategory {
        return BillCategory("148", "123")
    }

    fun makeBillCategoryView(): BillCategoryView {
        return BillCategoryView("148", "123")
    }

    fun makeBillView(): BillView {
        return BillView("148", "Zuku", "320320",
                "143672", "4900", "123",
                DataFactory.randomLong())
    }

    fun makeBill(): Bill {
        return Bill("148", "Zuku", "320320",
                "143672", "4900", "123",
                DataFactory.randomLong())
    }

    fun makeBillList(count: Int): List<Bill> {
        val bills = mutableListOf<Bill>()
        repeat(count) {
            bills.add(BillsFactory.makeBill())
        }
        return bills
    }

    fun makeCategoryList(count: Int): List<Bill> {
        val bills = mutableListOf<Bill>()
        repeat(count) {
            bills.add(BillsFactory.makeBill())
        }
        return bills
    }

    fun makeBillViewList(count: Int): List<BillView> {
        val billEntities = mutableListOf<BillView>()
        repeat(count) {
            billEntities.add(BillsFactory.makeBillView())
        }
        return billEntities
    }
}
