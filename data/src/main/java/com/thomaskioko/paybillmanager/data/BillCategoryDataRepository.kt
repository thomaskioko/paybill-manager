package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.BillCategoryMapper
import com.thomaskioko.paybillmanager.data.store.billcategory.BillCategoryDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import io.reactivex.Observable
import javax.inject.Inject

class BillCategoryDataRepository @Inject constructor(
        private val mapper: BillCategoryMapper,
        private val factory: BillCategoryDataStoreFactory
) : BillCategoryRepository {

    override fun getBillCategory(billId: String, categoryId: String): Observable<BillCategory> {
        return factory.getCacheDataStore().getBillCategory(billId, categoryId).toObservable()
                .map { mapper.mapFromEntity(it) }
    }

}