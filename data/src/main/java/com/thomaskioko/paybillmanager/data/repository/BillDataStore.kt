package com.thomaskioko.paybillmanager.data.repository

import com.thomaskioko.paybillmanager.data.model.BillEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Interface used to help communicate with external sources. They act as an access point. Allows us to avoid having direct access to source implementation
 */
interface BillDataStore {

    fun deleteBills(): Completable

    fun createBill(billEntity: BillEntity): Completable

    fun createBills(billEntities: List<BillEntity>): Completable

    fun getBills(): Flowable<List<BillEntity>>

    fun getBillById(billId: Int): Flowable<BillEntity>

    fun updateBill(billEntity: BillEntity): Completable
}