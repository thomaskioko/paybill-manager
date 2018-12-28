package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.factory.DataFactory.randomInt
import com.thomaskioko.paybillmanager.cache.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.data.model.CategoryEntity

object CategoryCachedFactory {

    fun makeCachedCategory(): CachedCategory {
        return CachedCategory("123", randomUuid(), randomInt())
    }

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity("123", randomUuid(), randomInt())
    }


    fun makeCategoryEntityList(count: Int): List<CategoryEntity> {
        val billEntities = mutableListOf<CategoryEntity>()
        repeat(count) {
            billEntities.add(makeCategoryEntity())
        }
        return billEntities
    }

}