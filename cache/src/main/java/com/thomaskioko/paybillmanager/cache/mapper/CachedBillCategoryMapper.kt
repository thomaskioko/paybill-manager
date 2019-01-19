package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedBillCategory
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import javax.inject.Inject

class CachedBillCategoryMapper @Inject constructor() : CacheMapper<CachedBillCategory, BillCategoryEntity> {

    override fun mapFromCached(type: CachedBillCategory): BillCategoryEntity {
        return BillCategoryEntity(type.billId, type.categoryId)
    }

    override fun mapToCached(type: BillCategoryEntity): CachedBillCategory {
        return CachedBillCategory(type.billId, type.categoryId)
    }

}