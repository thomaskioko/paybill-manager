package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Flowable

interface BillCategoryRepository {

    fun getBillCategory(billId: String, categoryId: String): Flowable<BillCategory>

    fun getBillsByCategoryId(categoryId: String): Flowable<List<Bill>>

    fun getCategoryByBillId(billId: String): Flowable<Category>

    fun createBillCategory(billCategory: BillCategory): Completable

}