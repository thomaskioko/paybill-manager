package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.BillsCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedBills
import com.thomaskioko.paybillmanager.data.model.BillEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedBillMapperTest {

    private val mapper = CachedBillMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = BillsCachedFactory.makeCachedBill()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = BillsCachedFactory.makeBillEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedBills, entity: BillEntity) {
        assertEquals(model.id, entity.billId)
        assertEquals(model.billName, entity.billName)
        assertEquals(model.paybillNumber, entity.paybillNumber)
        assertEquals(model.accountNumber, entity.accountNumber)
        assertEquals(model.categoryId, entity.categoryId)
        assertEquals(model.reminderDate, entity.reminderDate)
    }
}