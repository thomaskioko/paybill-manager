package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedBills
import com.thomaskioko.paybillmanager.data.model.BillEntity


object BillsCachedFactory {

    fun makeCachedBill(): CachedBills {
        return CachedBills(DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }

    fun makeCachedBill(billName: String): CachedBills {
        return CachedBills(DataFactory.randomUuid(), billName, DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }


    fun makeBillEntity(): BillEntity {
        return BillEntity(DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomInt(), DataFactory.randomLong()
        )
    }

    fun makeBillEntity(billId: String): BillEntity {
        return BillEntity(billId, DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomInt(),
                DataFactory.randomLong())
    }


    fun makeBillEntityList(count: Int): List<BillEntity> {
        val billEntities = mutableListOf<BillEntity>()
        repeat(count) {
            billEntities.add(makeBillEntity())
        }
        return billEntities
    }


}