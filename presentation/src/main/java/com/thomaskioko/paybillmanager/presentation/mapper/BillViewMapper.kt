package com.thomaskioko.paybillmanager.presentation.mapper

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.model.BillView
import javax.inject.Inject

class BillViewMapper @Inject constructor(): Mapper<BillView, Bill>{

    override fun mapToView(type: Bill): BillView {
       return BillView (type.billId, type.billName, type.paybillNumber, type.accountNumber,
        type.amount, type.categoryId, type.reminderDate)
    }
}