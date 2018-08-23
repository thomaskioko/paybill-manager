package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.store.BillsDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class BillsDataRepository @Inject constructor(
        private val mapper: BillMapper,
        private val factory: BillsDataStoreFactory
) : BillsRepository {

    override fun getBillById(billId: Int): Observable<Bill> {
        return factory.getCacheDataStore().getBillById(billId).toObservable()
                .map { mapper.mapFromEntity(it) }
    }

    override fun getBills(): Observable<List<Bill>> {
        return factory.getCacheDataStore().getBills().toObservable()
                .map { it.map { mapper.mapFromEntity(it) } }
    }

    override fun createBill(bill: Bill): Completable {
        return factory.getCacheDataStore().createBill(mapper.mapToEntity(bill))
    }

    override fun updateBill(bill: Bill): Completable {
        return factory.getCacheDataStore().updateBill(mapper.mapToEntity(bill))
    }

}