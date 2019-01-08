package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.store.category.CategoryDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CategoryDataRepository @Inject constructor(
        private val mapper: CategoryMapper,
        private val factory: CategoryDataStoreFactory
) : CategoryRepository {

    override fun createCategories(categories: List<Category>): Completable {
        return factory.getCacheDataStore().createCategories(
                categories.map { mapper.mapToEntity(it) }
        )
    }

    override fun getCategoryById(categoryId: String): Flowable<Category> {
        return factory.getCacheDataStore().getCategoryById(categoryId)
                .map { mapper.mapFromEntity(it) }
    }

    override fun getCategories(): Flowable<List<Category>> {
        return factory.getCacheDataStore().getCategories()
                .map { it.map { mapper.mapFromEntity(it) } }
    }

    override fun createCategory(category: Category): Completable {
        return factory.getCacheDataStore().createCategory(mapper.mapToEntity(category))
    }

    override fun updateCategory(category: Category): Completable {
        return factory.getCacheDataStore().updateCategory(mapper.mapToEntity(category))
    }

}