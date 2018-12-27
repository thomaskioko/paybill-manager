package com.thomaskioko.paybillmanager.data.test.factory

import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory.randomUuid
import com.thomaskioko.paybillmanager.domain.model.BillCategory

object BillCategoryFactory {

    fun makeBillCategory(): BillCategory {
        return BillCategory(randomUuid(), randomUuid())
    }

    fun makeBillCategoryEntity(): BillCategoryEntity {
        return BillCategoryEntity(randomUuid(), randomUuid())
    }
}