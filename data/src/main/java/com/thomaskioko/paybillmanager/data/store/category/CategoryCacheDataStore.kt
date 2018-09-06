package com.thomaskioko.paybillmanager.data.store.category

import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.bills.BillDataStore
import com.thomaskioko.paybillmanager.data.repository.bills.BillsCache
import com.thomaskioko.paybillmanager.data.repository.category.CategoryCache
import com.thomaskioko.paybillmanager.data.repository.category.CategoryDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class CategoryCacheDataStore @Inject constructor(
        private val categoryCache: CategoryCache
) : CategoryDataStore {

    override fun getCategoryById(categoryId: String): Flowable<CategoryEntity> {
        return categoryCache.getCategoryById(categoryId)
    }

    override fun createCategory(categoryEntity: CategoryEntity): Completable {
        return categoryCache.createCategory(categoryEntity)
    }

    override fun createCategories(categoryEntity: List<CategoryEntity>): Completable {
        return categoryCache.createCategories(categoryEntity)
    }

    override fun updateCategory(categoryEntity: CategoryEntity): Completable {
        return categoryCache.updateCategory(categoryEntity)
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return categoryCache.getCategories()
    }


    override fun deleteCategory(): Completable {
        return categoryCache.deleteCategories()
    }


}