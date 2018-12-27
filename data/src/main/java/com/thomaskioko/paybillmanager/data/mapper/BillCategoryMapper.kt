package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import javax.inject.Inject

open class BillCategoryMapper @Inject constructor() :
        EntityMapper<BillCategoryEntity, BillCategory> {


    override fun mapToEntity(domain: BillCategory): BillCategoryEntity {
        return BillCategoryEntity(domain.billId, domain.categoryId)
    }

    override fun mapFromEntity(entity: BillCategoryEntity): BillCategory {
        return BillCategory(entity.billId, entity.categoryId)
    }

}