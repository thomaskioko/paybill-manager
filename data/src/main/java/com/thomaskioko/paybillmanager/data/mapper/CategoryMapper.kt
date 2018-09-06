package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.domain.model.Category
import javax.inject.Inject

open class CategoryMapper @Inject constructor() : EntityMapper<CategoryEntity, Category> {

    override fun mapFromEntity(entity: CategoryEntity): Category {
        return Category(entity.id, entity.categoryName, entity.drawableUrl)
    }

    override fun mapToEntity(domain: Category): CategoryEntity {
        return CategoryEntity(domain.id, domain.categoryName, domain.drawableUrl)
    }

}