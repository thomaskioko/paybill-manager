package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.BillCategoryMapper
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.test.factory.BillCategoryFactory
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillCategoryMapperTest {

    private val mapper = BillCategoryMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = BillCategoryFactory.makeBillCategoryEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = BillCategoryFactory.makeBillCategory()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: BillCategoryEntity, billCategory: BillCategory) {
        assertEquals(entity.billId, billCategory.billId)
        assertEquals(entity.categoryId, billCategory.categoryId)

    }
}