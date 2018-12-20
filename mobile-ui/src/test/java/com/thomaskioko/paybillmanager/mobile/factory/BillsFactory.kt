package com.thomaskioko.paybillmanager.mobile.factory

import com.thomaskioko.paybillmanager.mobile.factory.DataFactory.randomInt
import com.thomaskioko.paybillmanager.mobile.factory.DataFactory.randomLong
import com.thomaskioko.paybillmanager.mobile.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.presentation.model.BillView


object BillsFactory {

    fun makeBillView(): BillView {
        return BillView(randomUuid(), randomUuid(), randomUuid(),
                randomUuid(), randomUuid(), randomInt(),
                randomLong())
    }

}
