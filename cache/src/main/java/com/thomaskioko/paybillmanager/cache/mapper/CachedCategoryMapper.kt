package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import javax.inject.Inject

class CachedCategoryMapper @Inject constructor() : CacheMapper<CachedCategory, CategoryEntity> {

    override fun mapFromCached(type: CachedCategory): CategoryEntity {
        return CategoryEntity(type.id, type.categoryName, type.drawableUrl)
    }

    override fun mapToCached(type: CategoryEntity): CachedCategory {
        return CachedCategory(type.id, type.categoryName, type.drawableUrl)
    }

}