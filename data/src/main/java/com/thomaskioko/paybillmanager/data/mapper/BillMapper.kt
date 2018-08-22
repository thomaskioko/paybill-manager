package com.thomaskioko.paybillmanager.data.mapper

import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.domain.model.Bill
import javax.inject.Inject

open class BillMapper @Inject constructor() : EntityMapper<BillEntity, Bill> {

    override fun mapFromEntity(entity: BillEntity): Bill {
        return Bill(entity.billId, entity.billName, entity.paybillNumber, entity.accountNumber,
                entity.amount, entity.categoryId, entity.reminderDate)
    }

    override fun mapToEntity(domain: Bill): BillEntity {
        return BillEntity(domain.billId, domain.billName, domain.paybillNumber, domain.accountNumber,
                domain.amount, domain.categoryId, domain.reminderDate)
    }

}