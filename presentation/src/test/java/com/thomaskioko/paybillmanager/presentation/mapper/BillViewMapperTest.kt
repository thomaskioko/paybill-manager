package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.model.BillView
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BillViewMapperTest {

    private val mapper = BillViewMapper()

    @Test
    fun mapFromCachedMapsData() {
        val bill = BillsFactory.makeBill()
        val billView = mapper.mapToView(bill)

        assertEqualData(bill, billView)
    }

    @Test
    fun mapToCachedMapsData() {
        val bill = BillsFactory.makeBill()
        val billView = mapper.mapToView(bill)

        assertEqualData(bill, billView)
    }

    private fun assertEqualData(model: Bill, entity: BillView) {
        assertEquals(model.billId, entity.billId)
        assertEquals(model.billName, entity.billName)
        assertEquals(model.paybillNumber, entity.paybillNumber)
        assertEquals(model.accountNumber, entity.accountNumber)
        assertEquals(model.categoryId, entity.categoryId)
        assertEquals(model.reminderDate, entity.reminderDate)
    }
}