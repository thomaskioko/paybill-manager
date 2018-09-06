package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.domain.model.Category

object CategoryDataFactory {

    fun makeCategory(): Category {
        return Category(randomUuid(), randomUuid(), randomUuid())
    }

    fun makeCategoryEntity(): CategoryEntity {
        return CategoryEntity(randomUuid(), randomUuid(), randomUuid())
    }

    fun makeCategoryList(count: Int): List<Category> {
        val bills = mutableListOf<Category>()
        repeat(count) {
            bills.add(makeCategory())
        }
        return bills
    }

    fun makeCategoryEntityList(count: Int): List<CategoryEntity> {
        val billEntities = mutableListOf<CategoryEntity>()
        repeat(count) {
            billEntities.add(makeCategoryEntity())
        }
        return billEntities
    }

}