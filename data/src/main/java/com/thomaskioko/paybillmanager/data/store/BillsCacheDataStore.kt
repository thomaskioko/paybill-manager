package com.thomaskioko.paybillmanager.data.store

import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.repository.BillsCache
import com.thomaskioko.paybillmanager.data.repository.BillDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class BillsCacheDataStore @Inject constructor(
        private val billsCache: BillsCache
) : BillDataStore {

    override fun getBillById(billId: Int): Flowable<BillEntity> {
        return billsCache.getBillById(billId)
    }

    override fun createBill(billEntity: BillEntity): Completable {
        return billsCache.createBill(billEntity)
    }

    override fun updateBill(billEntity: BillEntity): Completable {
       return billsCache.updateBill(billEntity)
    }

    override fun getBills(): Flowable<List<BillEntity>> {
        return billsCache.getBills()
    }


    override fun deleteBills(): Completable {
        return billsCache.deleteBills()
    }


}