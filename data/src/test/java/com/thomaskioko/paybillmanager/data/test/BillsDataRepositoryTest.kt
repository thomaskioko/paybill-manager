package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.BillsDataRepository
import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.repository.BillDataStore
import com.thomaskioko.paybillmanager.data.store.BillsDataStoreFactory
import com.thomaskioko.paybillmanager.data.test.factory.BillsDataFactory
import com.thomaskioko.paybillmanager.domain.model.Bill
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class BillsDataRepositoryTest {

    private val mapper = mock<BillMapper>()
    private val factory = mock<BillsDataStoreFactory>()
    private val store = mock<BillDataStore>()
    private val repository = BillsDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()
    }

    @Test
    fun getBillsCompletes() {
        stubGetBills(Flowable.just(BillsDataFactory.makeBillEntityList(2)))

        stubMapper(BillsDataFactory.makeBill(), any())

        val testObserver = repository.getBills().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillsReturnsData() {
        val billEntity = BillsDataFactory.makeBillEntity()
        val bill = BillsDataFactory.makeBill()

        stubGetBills(Flowable.just(listOf(billEntity)))

        stubMapper(bill, billEntity)

        val testObserver = repository.getBills().test()
        testObserver.assertValue(listOf(bill))
    }


    @Test
    fun createBillCompletes() {

        stubCreateBill(Completable.complete())

        val testObserver = store.createBill(BillsDataFactory.makeBillEntity()).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateBillCompletes() {

        stubUpdateBill(Completable.complete())

        val testObserver = store.updateBill(BillsDataFactory.makeBillEntity()).test()
        testObserver.assertComplete()
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(store)
    }

    private fun stubCreateBill(completable: Completable) {
        whenever(store.createBill(any()))
                .thenReturn(completable)
    }

    private fun stubUpdateBill(completable: Completable) {
        whenever(store.updateBill(any()))
                .thenReturn(completable)
    }

    private fun stubGetBills(observable: Flowable<List<BillEntity>>) {
        whenever(store.getBills())
                .thenReturn(observable)
    }

    private fun stubMapper(model: Bill, entity: BillEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

}