package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.presentation.model.BillCategoryView
import javax.inject.Inject

class BillCategoryViewMapper @Inject constructor() : Mapper<BillCategoryView, BillCategory> {

    override fun mapToView(type: BillCategory): BillCategoryView {
        return BillCategoryView(type.billId, type.categoryId)
    }
}
