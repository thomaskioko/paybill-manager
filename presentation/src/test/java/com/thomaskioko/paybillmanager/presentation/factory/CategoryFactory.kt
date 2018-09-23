package com.thomaskioko.paybillmanager.presentation.factory

import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.presentation.model.CategoryView


object CategoryFactory {

    fun makeStaticCategory(): Category {
        return Category("12", "Utilities", 123456
        )
    }

    fun makeCategory(): Category {
        return Category(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt())
    }


    fun makeCategoryList(count: Int): List<Category> {
        val billEntities = mutableListOf<Category>()
        repeat(count) {
            billEntities.add(makeCategory())
        }
        return billEntities
    }

    fun makeCategoryView(): CategoryView {
        return CategoryView(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt())
    }

    fun makeCategoryViewList(count: Int): List<CategoryView> {
        val billEntities = mutableListOf<CategoryView>()
        repeat(count) {
            billEntities.add(makeCategoryView())
        }
        return billEntities
    }


}