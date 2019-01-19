package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedBill
import com.thomaskioko.paybillmanager.cache.model.CachedBillCategory
import com.thomaskioko.paybillmanager.cache.model.CachedCategory
import com.thomaskioko.paybillmanager.data.model.BillCategoryEntity

object BillCategoryCachedFactory {


    fun makeBillCategoryEntity(): BillCategoryEntity {
        return BillCategoryEntity("148", "123")
    }

    fun makeCachedBill(): CachedBill {
        return CachedBill("148", "Zuku", "320320",
                "143672", "4900", "123",
                DataFactory.randomLong())
    }

    fun makeCachedCategory(): CachedCategory {
        return CachedCategory("123","Internet", DataFactory.randomInt())
    }


    fun makeCachedBillCategory(): CachedBillCategory {
        return CachedBillCategory("148", "123")
    }

}