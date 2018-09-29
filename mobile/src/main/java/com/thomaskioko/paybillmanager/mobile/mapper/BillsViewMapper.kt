package com.thomaskioko.paybillmanager.mobile.mapper

import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.presentation.model.BillView
import javax.inject.Inject

open class BillsViewMapper @Inject constructor(): ViewMapper<BillView, Bill>{


    override fun mapToView(presentation: BillView): Bill {
        return Bill (presentation.billId, presentation.billName, presentation.paybillNumber,
                presentation.accountNumber,
                presentation.amount, presentation.categoryId, presentation.reminderDate)
    }

}