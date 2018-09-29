package com.thomaskioko.paybillmanager.mobile.mapper

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.factory.BillsFactory
import com.thomaskioko.paybillmanager.presentation.model.BillView
import junit.framework.TestCase.assertEquals
import org.junit.Test

class BillsViewMapperTest {
    private val mapper = BillsViewMapper()

    @Test
    fun mapToView() {
        val billView = BillsFactory.makeBillView()
        val bill = mapper.mapToView(billView)

        assertEqualData(bill, billView)
    }

    private fun assertEqualData(model: Bill, billView: BillView) {
        assertEquals(model.billId, billView.billId)
        assertEquals(model.billName, billView.billName)
        assertEquals(model.paybillNumber, billView.paybillNumber)
        assertEquals(model.accountNumber, billView.accountNumber)
        assertEquals(model.amount, billView.amount)
        assertEquals(model.categoryId, billView.categoryId)
        assertEquals(model.reminderDate, billView.reminderDate)
    }
}
