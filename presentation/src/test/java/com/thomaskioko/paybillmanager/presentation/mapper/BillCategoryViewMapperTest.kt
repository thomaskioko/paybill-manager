package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.presentation.factory.DataFactory
import com.thomaskioko.paybillmanager.presentation.model.BillCategoryView
import junit.framework.TestCase
import org.junit.Test

class BillCategoryViewMapperTest {

    private val mapper = BillCategoryViewMapper()

    @Test
    fun mapToView() {
        val model = DataFactory.makeBillCategory()
        val entity = mapper.mapToView(model)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: BillCategory, entity: BillCategoryView) {
        TestCase.assertEquals(model.billId, entity.billId)
        TestCase.assertEquals(model.categoryId, entity.categoryId)
    }
}
