package com.thomaskioko.paybillmanager.cache.factory

import com.thomaskioko.paybillmanager.cache.model.CachedBill
import com.thomaskioko.paybillmanager.data.model.BillEntity


object BillsCachedFactory {

    fun makeCachedBill(): CachedBill {
        return CachedBill("148", "Zuku", "320320",
                "143672", "4900", "123",
                DataFactory.randomLong())
    }

    fun makeCachedBill(billName: String): CachedBill {
        return CachedBill(DataFactory.randomUuid(), billName, DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomLong())
    }


    fun makeBillEntity(): BillEntity {
        return BillEntity("148", "Zuku", "320320",
                "143672", "4900", "123", DataFactory.randomLong()
        )
    }

    fun makeBillEntity(billId: String): BillEntity {
        return BillEntity(billId, DataFactory.randomUuid(), DataFactory.randomUuid(),
                DataFactory.randomUuid(), DataFactory.randomUuid(), DataFactory.randomUuid(),
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