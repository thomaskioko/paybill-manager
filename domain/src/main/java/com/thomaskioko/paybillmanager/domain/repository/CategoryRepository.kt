package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Flowable

interface CategoryRepository {

    fun getCategories(): Flowable<List<Category>>

    fun getCategoryById(categoryId: String): Flowable<Category>

    fun createCategory(category: Category): Completable

    fun createCategories(categories: List<Category>): Completable

    fun updateCategory(category: Category): Completable
}