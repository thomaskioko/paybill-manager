package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedBills
import com.thomaskioko.paybillmanager.data.model.BillEntity


object BillsCachedFactory {

    fun makeCachedBill(): CachedBills {
        return CachedBills(DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }

    fun makeUpdateCachedBill(billName: String): CachedBills {
        return CachedBills(billName, DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }


    fun makeBillEntity(): BillEntity {
        return BillEntity(DataFactory.randomInt(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }


}