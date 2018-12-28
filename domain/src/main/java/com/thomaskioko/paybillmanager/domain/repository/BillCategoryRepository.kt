package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.domain.model.Category
import io.reactivex.Completable
import io.reactivex.Observable

interface BillCategoryRepository {

    fun getBillCategory(billId: String, categoryId: String): Observable<BillCategory>

    fun getBillsByCategoryId(categoryId: String): Observable<List<Bill>>

    fun getCategoryByBillId(billId: String): Observable<Category>

    fun createBillCategory(billCategory: BillCategory): Completable

}