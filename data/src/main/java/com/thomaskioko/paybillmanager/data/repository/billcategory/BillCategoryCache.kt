package com.thomaskioko.paybillmanager.data.repository.billcategory

import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface BillCategoryCache {

    fun createBillCategory(billCategoryEntity: BillCategoryEntity): Completable

    fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategoryEntity>

    fun updateBillCategory(billCategoryEntity: BillCategoryEntity): Completable
}