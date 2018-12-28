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
import io.reactivex.Observable
import javax.inject.Inject

class BillCategoryDataRepository @Inject constructor(
        private val billMapper: BillMapper,
        private val billCategoryMapper: BillCategoryMapper,
        private val categoryMapper: CategoryMapper,
        private val factory: BillCategoryDataStoreFactory
) : BillCategoryRepository {


    override fun getBillCategory(billId: String, categoryId: String): Observable<BillCategory> {
        return factory.getCacheDataStore().getBillCategory(billId, categoryId)
                .toObservable()
                .map { billCategoryMapper.mapFromEntity(it) }
    }

    override fun createBillCategory(billCategory: BillCategory): Completable {
       return factory.getCacheDataStore().createBillCategory(billCategoryMapper.mapToEntity(billCategory))
    }

    override fun getBillsByCategoryId(categoryId: String): Observable<List<Bill>> {
        return factory.getCacheDataStore().getBillsByCategoryId(categoryId).toObservable().map {
            it.map { billMapper.mapFromEntity(it) }
        }
    }

    override fun getCategoryByBillId(billId: String): Observable<Category> {
        return factory.getCacheDataStore().getCategoryByBillId(billId).toObservable()
                .map { categoryMapper.mapFromEntity(it) }
    }

}