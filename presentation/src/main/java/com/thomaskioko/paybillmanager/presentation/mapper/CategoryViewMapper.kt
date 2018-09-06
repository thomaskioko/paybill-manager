package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import javax.inject.Inject

open class CategoryViewMapper @Inject constructor(): Mapper<CategoryView, Category>{

    override fun mapToView(type: Category): CategoryView {
       return CategoryView (type.id, type.categoryName, type.drawableUrl)
    }
}