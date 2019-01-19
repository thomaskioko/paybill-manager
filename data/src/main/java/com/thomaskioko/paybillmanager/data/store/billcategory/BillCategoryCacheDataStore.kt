package com.thomaskioko.paybillmanager.data.store.billcategory

import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class BillCategoryCacheDataStore @Inject constructor(
        private val billCategoryCache: BillCategoryCache
) : BillCategoryDataStore {

    override fun createBillCategory(billCategoryEntity: BillCategoryEntity): Completable {
        return billCategoryCache.createBillCategory(billCategoryEntity)
    }

    override fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategoryEntity> {
        return billCategoryCache.getBillCategory(billId, categoryId)
    }

    override fun updateBillCategory(billCategoryEntity: BillCategoryEntity): Completable {
        return billCategoryCache.updateBillCategory(billCategoryEntity)
    }

    override fun getBillsByCategoryId(categoryId: String): Flowable<List<BillEntity>> {
        return billCategoryCache.getBillsByCategoryId(categoryId)
    }

    override fun getCategoryByBillId(billId: String): Flowable<CategoryEntity> {
        return billCategoryCache.getCategoryByBillId(billId)
    }

}