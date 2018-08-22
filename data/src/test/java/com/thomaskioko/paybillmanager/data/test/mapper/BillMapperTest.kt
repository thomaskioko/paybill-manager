package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.test.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillMapperTest {

    private val mapper = BillMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = BillsDataFactory.makeBillEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = BillsDataFactory.makeBill()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BillEntity, model: Bill) {
        assertEquals(entity.billId, model.billId)
        assertEquals(entity.billName, model.billName)
        assertEquals(entity.paybillNumber, model.paybillNumber)
        assertEquals(entity.accountNumber, model.accountNumber)
        assertEquals(entity.categoryId, model.categoryId)
    }
}