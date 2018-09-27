package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryViewMapperTest {

    private val mapper = CategoryViewMapper()

    @Test
    fun mapFromCachedMapsData() {
        val category = CategoryFactory.makeCategory()
        val categoryView = mapper.mapToView(category)

        assertEqualData(category, categoryView)
    }

    @Test
    fun mapToCachedMapsData() {
        val category = CategoryFactory.makeCategory()
        val categoryView = mapper.mapToView(category)

        assertEqualData(category, categoryView)
    }

    private fun assertEqualData(model: Category, entity: CategoryView) {
        assertEquals(model.id, entity.id)
        assertEquals(model.categoryName, entity.categoryName)
        assertEquals(model.drawableUrl, entity.drawableUrl)
    }
}