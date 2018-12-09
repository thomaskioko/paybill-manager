package com.thomaskioko.paybillmanager.data.repository.category

import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Defines abstract methods to be implemented by the cash module.
 */

interface CategoryCache {

    fun createCategory(categoryEntity: CategoryEntity): Completable

    fun createCategories(categoryEntity: List<CategoryEntity>): Completable

    fun getCategories(): Flowable<List<CategoryEntity>>

    fun getCategoryById(categoryId: String): Flowable<CategoryEntity>

    fun updateCategory(categoryEntity: CategoryEntity): Completable

    fun deleteCategories(): Completable
}