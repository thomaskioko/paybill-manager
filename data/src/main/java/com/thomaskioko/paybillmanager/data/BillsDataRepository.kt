package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.store.bills.BillsDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class BillsDataRepository @Inject constructor(
        private val mapper: BillMapper,
        private val factory: BillsDataStoreFactory
) : BillsRepository {


    override fun createBills(bills: List<Bill>): Completable {
        return factory.getCacheDataStore().createBills(
                bills.map { mapper.mapToEntity(it) }
        )
    }

    override fun getBillByBillId(billId: String): Flowable<Bill> {
        return factory.getCacheDataStore().getBillByBillId(billId)
                .map { mapper.mapFromEntity(it) }
    }

    override fun getBillByIds(billId: String, categoryId: String): Flowable<Bill> {
        return factory.getCacheDataStore().getBillByBillId(billId)
                .map { mapper.mapFromEntity(it) }
    }

    override fun getBills(): Flowable<List<Bill>> {
        return factory.getCacheDataStore().getBills()
                .map { it.map { mapper.mapFromEntity(it) } }
    }

    override fun createBill(bill: Bill): Completable {
        return factory.getCacheDataStore().createBill(mapper.mapToEntity(bill))
    }

    override fun updateBill(bill: Bill): Completable {
        return factory.getCacheDataStore().updateBill(mapper.mapToEntity(bill))
    }

}