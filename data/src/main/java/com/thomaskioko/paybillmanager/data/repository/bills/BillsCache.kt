package com.thomaskioko.paybillmanager.data.repository.bills

import com.thomaskioko.paybillmanager.data.model.BillEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Defines abstract methods to be implemented by the cash module.
 */

interface BillsCache {

    fun deleteBills(): Completable

    fun createBill(billEntity: BillEntity): Completable

    fun createBills(billEntity: List<BillEntity>): Completable

    fun getBills(): Flowable<List<BillEntity>>

    fun getBillByBillId(billId: String): Flowable<BillEntity>

    fun getBillByIds(billId: String, categoryId: String): Flowable<BillEntity>

    fun updateBill(billEntity: BillEntity): Completable
}