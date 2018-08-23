package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedBills
import com.thomaskioko.paybillmanager.data.model.BillEntity
import javax.inject.Inject

class CachedBillMapper @Inject constructor() : CacheMapper<CachedBills, BillEntity> {

    override fun mapFromCached(type: CachedBills): BillEntity {
        return BillEntity(type.id, type.billName, type.paybillNumber, type.accountNumber,
                type.amount, type.categoryId, type.reminderDate)
    }

    override fun mapToCached(type: BillEntity): CachedBills {
        return CachedBills(type.billName, type.paybillNumber, type.accountNumber,
                type.amount, type.categoryId, type.reminderDate)
    }

}