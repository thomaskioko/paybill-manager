package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.BillCategoryMapper
import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.mapper.CategoryMapper
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BillCategoryDataRepository @Inject constructor(
        private val billMapper: BillMapper,
        private val billCategoryMapper: BillCategoryMapper,
        private val categoryMapper: CategoryMapper,
        private val factory: BillCategoryDataStoreFactory
) : BillCategoryRepository {


    override fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategory> {
        return factory.getCacheDataStore().getBillCategory(billId, categoryId)
                .map { billCategoryMapper.mapFromEntity(it) }
    }

    override fun createBillCategory(billCategory: BillCategory): Completable {
        return factory.getCacheDataStore().createBillCategory(billCategoryMapper.mapToEntity(billCategory))
    }

    override fun getBillsByCategoryId(categoryId: String): Flowable<List<Bill>> {
        return factory.getCacheDataStore().getBillsByCategoryId(categoryId)
                .map {
                    it.map { billMapper.mapFromEntity(it) }
                }
    }

    override fun getCategoryByBillId(billId: String): Flowable<Category> {
        return factory.getCacheDataStore().getCategoryByBillId(billId)
                .map { categoryMapper.mapFromEntity(it) }
    }

}