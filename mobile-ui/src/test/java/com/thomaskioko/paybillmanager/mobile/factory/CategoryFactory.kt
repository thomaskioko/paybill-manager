package com.thomaskioko.paybillmanager.mobile.factory

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.model.CategoryView


object CategoryFactory {

    fun makeCategoryView(): CategoryView {
        return CategoryView(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt())
    }



}