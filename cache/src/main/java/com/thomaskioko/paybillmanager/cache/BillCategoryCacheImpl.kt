package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillCategoryMapper
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillMapper
import com.thomaskioko.paybillmanager.cache.mapper.CachedCategoryMapper
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BillCategoryCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val categoryMapper: CachedCategoryMapper,
        private val billCategoryMapper: CachedBillCategoryMapper,
        private val cachedBillMapper: CachedBillMapper
) : BillCategoryCache {

    override fun createBillCategory(billCategoryEntity: BillCategoryEntity): Completable {
        return Completable.defer {
            database.billCategoryDao()
                    .insertBillCategory(billCategoryMapper.mapToCached(billCategoryEntity))
            Completable.complete()
        }
    }

    override fun updateBillCategory(billCategoryEntity: BillCategoryEntity): Completable {
        return Completable.defer {
            database.billCategoryDao()
                    .updateBillCategory(billCategoryMapper.mapToCached(billCategoryEntity))
            Completable.complete()
        }
    }


    override fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategoryEntity> {
        return database.billCategoryDao().getBillCategory(billId, categoryId)
                .map { billCategoryMapper.mapFromCached(it) }
    }

    override fun getBillsByCategoryId(categoryId: String): Flowable<List<BillEntity>> {
        return database.billCategoryDao().getBills(categoryId)
                .map {
                    it.map { cachedBillMapper.mapFromCached(it) }
                }
    }

    override fun getCategoryByBillId(billId: String): Flowable<CategoryEntity> {
        return database.billCategoryDao().getCategory(billId)
                .map { categoryMapper.mapFromCached(it) }
    }

}