package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedBillMapper
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.repository.BillsCache
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BiilsCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val mapper: CachedBillMapper
) : BillsCache {

    override fun deleteBills(): Completable {
        return Completable.defer {
            database.billsDao().deleteBills()
            Completable.complete()
        }
    }

    override fun createBill(billEntity: BillEntity): Completable {
        return Completable.defer {
            database.billsDao().insertBill(mapper.mapToCached(billEntity))
            Completable.complete()
        }
    }

    override fun getBills(): Flowable<List<BillEntity>> {
        return database.billsDao().getBills()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun updateBill(billEntity: BillEntity): Completable {
        return Completable.defer {
            database.billsDao().updateBill(mapper.mapToCached(billEntity))
            Completable.complete()
        }
    }

}