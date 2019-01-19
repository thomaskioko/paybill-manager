package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Bill
import io.reactivex.Completable
import io.reactivex.Flowable

interface BillsRepository {

    fun getBills(): Flowable<List<Bill>>

    fun getBillByBillId(billId: String): Flowable<Bill>

    fun getBillByIds(billId: String, categoryId: String): Flowable<Bill>

    fun createBill(bill: Bill): Completable

    fun createBills(bills: List<Bill>): Completable

    fun updateBill(bill: Bill): Completable
}