package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.factory.CategoryCachedFactory
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@RunWith(JUnit4::class)
class CachedCategoryMapperTest {

    private val mapper = CachedCategoryMapper()

    @Test
    fun mapFromCachedMapsData() {
        val model = CategoryCachedFactory.makeCachedCategory()
        val entity = mapper.mapFromCached(model)

        assertEqualData(model, entity)
    }

    @Test
    fun mapToCachedMapsData() {
        val entity = CategoryCachedFactory.makeCategoryEntity()
        val model = mapper.mapToCached(entity)

        assertEqualData(model, entity)
    }

    private fun assertEqualData(model: CachedCategory, entity: CategoryEntity) {
        assertEquals(model.id, entity.id)
        assertEquals(model.categoryName, entity.categoryName)
        assertEquals(model.drawableUrl, entity.drawableUrl)
    }
}