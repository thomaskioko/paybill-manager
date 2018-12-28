package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.BillCategoryCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedBillCategory
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals


@RunWith(JUnit4::class)
class CachedBillCategoryMapperTest {

    private val mapper = CachedBillCategoryMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = BillCategoryCachedFactory.makeCachedBillCategory()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = BillCategoryCachedFactory.makeBillCategoryEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)

    }

    private fun assertEqualData(model: CachedBillCategory, entity: BillCategoryEntity) {
        assertEquals(model.billId, entity.billId)
        assertEquals(model.categoryId, entity.categoryId)
    }
}