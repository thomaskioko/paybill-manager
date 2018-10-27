package com.thomaskioko.paybillmanager.mobile.mapper

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.factory.CategoryFactory
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import junit.framework.TestCase.assertEquals
import org.junit.Test

class CategoryViewMapperTest {
    private val mapper = CategoryViewMapper()

    @Test
    fun mapFromCachedMapsData() {
        val categoryView = CategoryFactory.makeCategoryView()
        val category = mapper.mapToView(categoryView)

        assertEqualData(category, categoryView)
    }

    private fun assertEqualData(model: Category, entity: CategoryView) {
        assertEquals(model.id, entity.id)
        assertEquals(model.categoryName, entity.categoryName)
        assertEquals(model.drawableUrl, entity.drawableUrl)
    }
}