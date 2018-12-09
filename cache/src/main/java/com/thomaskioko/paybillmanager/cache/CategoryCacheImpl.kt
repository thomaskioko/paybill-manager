package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedCategoryMapper
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.category.CategoryCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoryCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val mapper: CachedCategoryMapper
) : CategoryCache {

    override fun createCategory(categoryEntity: CategoryEntity): Completable {
        return Completable.defer {
            database.categoryDaoDao().insertCachedCategory(mapper.mapToCached(categoryEntity))
            Completable.complete()
        }
    }

    override fun createCategories(categoryEntity: List<CategoryEntity>): Completable {
        return Completable.defer {
            database.categoryDaoDao().insertCachedCategories(
                    categoryEntity.map { mapper.mapToCached(it) }
            )
            Completable.complete()
        }
    }

    override fun updateCategory(categoryEntity: CategoryEntity): Completable {
        return Completable.defer {
            database.categoryDaoDao().updateCachedCategory(mapper.mapToCached(categoryEntity))
            Completable.complete()
        }
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        return database.categoryDaoDao().getCachedCategories()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun getCategoryById(categoryId: String): Flowable<CategoryEntity> {
        return database.categoryDaoDao().getCachedCategoryById(categoryId)
                .map { mapper.mapFromCached(it) }
    }

    override fun deleteCategories(): Completable {
        return Completable.defer {
            database.categoryDaoDao().deleteCachedCategories()
            Completable.complete()
        }
    }

}