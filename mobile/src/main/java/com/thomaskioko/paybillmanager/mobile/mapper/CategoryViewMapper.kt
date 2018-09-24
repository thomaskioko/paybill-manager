package com.thomaskioko.paybillmanager.mobile.mapper

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import javax.inject.Inject

class CategoryViewMapper @Inject constructor() : ViewMapper<CategoryView, Category> {

    override fun mapToView(presentation: CategoryView): Category {
        return Category(presentation.id, presentation.categoryName, presentation.drawableUrl)

    }

}