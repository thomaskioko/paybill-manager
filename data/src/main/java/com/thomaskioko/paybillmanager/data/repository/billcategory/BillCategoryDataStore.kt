package com.thomaskioko.paybillmanager.data.repository.billcategory

import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.model.CategoryEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface BillCategoryDataStore {

    fun createBillCategory(billCategoryEntity: BillCategoryEntity): Completable

    fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategoryEntity>

    fun getBillsByCategoryId(categoryId: String): Flowable<List<BillEntity>>

    fun getCategoryByBillId(billId: String): Flowable<CategoryEntity>

    fun updateBillCategory(billCategoryEntity: BillCategoryEntity): Completable
}