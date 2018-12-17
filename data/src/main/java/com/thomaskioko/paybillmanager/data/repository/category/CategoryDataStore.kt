package com.thomaskioko.paybillmanager.data.repository.category

import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface used to help communicate with external sources. They act as an access point. Allows us to avoid having direct access to source implementation
 */
interface CategoryDataStore {

    fun deleteCategory(): Completable

    fun createCategory(categoryEntity: CategoryEntity): Completable

    fun createCategories(categoryEntity: List<CategoryEntity>): Completable

    fun getCategories(): Flowable<List<CategoryEntity>>

    fun getCategoryById(categoryId: String): Flowable<CategoryEntity>

    fun updateCategory(categoryEntity: CategoryEntity): Completable
}