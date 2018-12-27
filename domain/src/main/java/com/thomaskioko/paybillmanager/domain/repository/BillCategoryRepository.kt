package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.BillCategory
import io.reactivex.Observable

interface BillCategoryRepository {

    fun getBillCategory(billId: String, categoryId: String): Observable<BillCategory>
}