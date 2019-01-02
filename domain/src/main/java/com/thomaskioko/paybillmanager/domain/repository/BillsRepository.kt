package com.thomaskioko.paybillmanager.domain.repository

import com.thomaskioko.paybillmanager.domain.model.Bill
import io.reactivex.Completable
import io.reactivex.Observable

interface BillsRepository {

    fun getBills(): Observable<List<Bill>>

    fun getBillByBillId(billId: String): Observable<Bill>

    fun getBillByIds(billId: String, categoryId: String): Observable<Bill>

    fun createBill(bill: Bill): Completable

    fun createBills(bills: List<Bill>): Completable

    fun updateBill(bill: Bill): Completable
}