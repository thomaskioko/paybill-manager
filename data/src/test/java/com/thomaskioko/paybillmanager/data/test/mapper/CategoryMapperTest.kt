package com.thomaskioko.paybillmanager.data.test.mapper

import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.test.factory.CategoryDataFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class CategoryMapperTest {

    private val mapper = CategoryMapper()

    @Test
    fun mapFromEntityMapsData() {
        val entity = CategoryDataFactory.makeCategoryEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun mapToEntityMapsData() {
        val model = CategoryDataFactory.makeCategory()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }

    private fun assertEqualData(entity: CategoryEntity, model: Category) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.categoryName, model.categoryName)
        assertEquals(entity.drawableUrl, model.drawableUrl)
    }
}