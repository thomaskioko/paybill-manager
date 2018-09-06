package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.data.model.CategoryEntity

object CategoryCachedFactory {

    fun makeCachedCategory(): CachedCategory {
        return CachedCategory(randomUuid(), randomUuid(), randomUuid())
    }

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(randomUuid(), randomUuid(), randomUuid())
    }


    fun makeCategoryEntityList(count: Int): List<CategoryEntity> {
        val billEntities = mutableListOf<CategoryEntity>()
        repeat(count) {
            billEntities.add(makeCategoryEntity())
        }
        return billEntities
    }

}