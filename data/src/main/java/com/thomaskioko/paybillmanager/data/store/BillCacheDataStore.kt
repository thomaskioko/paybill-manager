package com.thomaskioko.paybillmanager.data.store

import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.repository.BillCache
import com.thomaskioko.paybillmanager.data.repository.BillDataStore
import com.thomaskioko.paybillmanager.domain.model.Bill
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class BillCacheDataStore @Inject constructor(
        private val billCache: BillCache
) : BillDataStore {
    override fun createBill(billEntity: BillEntity): Completable {
        return billCache.createBill(billEntity)
    }

    override fun updateBill(billEntity: BillEntity): Completable {
       return billCache.updateBill(billEntity)
    }

    override fun deleteBills(): Completable {
        return billCache.deleteBills()
    }


    override fun getBills(): Flowable<List<BillEntity>> {
        return billCache.getBills()
    }


}