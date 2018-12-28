package com.thomaskioko.paybillmanager.cache.mapper

import com.thomaskioko.paybillmanager.cache.model.CachedBill
import com.thomaskioko.paybillmanager.data.model.BillEntity
import javax.inject.Inject

class CachedBillMapper @Inject constructor() : CacheMapper<CachedBill, BillEntity> {

    override fun mapFromCached(type: CachedBill): BillEntity {
        return BillEntity(type.id, type.billName, type.paybillNumber, type.accountNumber,
                type.amount, type.categoryId, type.reminderDate)
    }

    override fun mapToCached(type: BillEntity): CachedBill {
        return CachedBill(type.billId, type.billName, type.paybillNumber, type.accountNumber,
                type.amount, type.categoryId, type.reminderDate)
    }

}