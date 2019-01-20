package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.thomaskioko.paybillmanager.data.BillsDataRepository
import com.thomaskioko.paybillmanager.data.mapper.BillMapper
import com.thomaskioko.paybillmanager.data.model.BillEntity
import com.thomaskioko.paybillmanager.data.store.bills.BillsCacheDataStore
import com.thomaskioko.paybillmanager.data.store.bills.BillsDataStoreFactory
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
    private var cacheDataStore = mock<BillsCacheDataStore>()
    private val repository = BillsDataRepository(mapper, factory)

    @Before
    fun setup() {
        stubFactoryGetCacheDataStore()
        stubMapperFromEntity(BillsDataFactory.makeBill(), BillsDataFactory.makeBillEntity())
        stubMapperToEntity(BillsDataFactory.makeBill(), BillsDataFactory.makeBillEntity())
    }

    @Test
    fun getBillsCompletes() {
        stubGetBills(Flowable.just(BillsDataFactory.makeBillEntityList(2)))

        stubMapperFromEntity(BillsDataFactory.makeBill(), any())

        val testObserver = repository.getBills().test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillsReturnsData() {
        val billEntity = BillsDataFactory.makeBillEntity()
        val bill = BillsDataFactory.makeBill()

        stubGetBills(Flowable.just(listOf(billEntity)))

        stubMapperFromEntity(bill, billEntity)

        val testObserver = repository.getBills().test()
        testObserver.assertValue(listOf(bill))
    }

    @Test
    fun getBillsByIdCompletes() {

        val bill = BillsDataFactory.makeBill()

        stubCreateBill(Completable.complete())

        repository.createBill(bill).test()

        stubGetBillById(Flowable.just(BillsDataFactory.makeBillEntity()))

        val testObserver = repository.getBillByBillId(bill.billId).test()
        testObserver.assertComplete()
    }

    @Test
    fun getBillByIdsCompletes() {

        val bill = BillsDataFactory.makeBill()
        stubCreateBill(Completable.complete())

        repository.createBill(bill).test()

        stubGetBillByIds(Flowable.just(BillsDataFactory.makeBillEntity()))

        val testObserver = repository.getBillByIds(bill.billId, bill.categoryId).test()
        testObserver.assertComplete()
    }


    @Test
    fun createBillCompletes() {

        stubCreateBill(Completable.complete())

        val testObserver = repository.createBill(BillsDataFactory.makeBill()).test()
        testObserver.assertComplete()
    }

    @Test
    fun createBillsCompletes() {

        stubCreateBills(Completable.complete())

        val testObserver = repository.createBills(listOf(BillsDataFactory.makeBill())).test()
        testObserver.assertComplete()
    }

    @Test
    fun updateBillCompletes() {

        stubUpdateBill(Completable.complete())

        val testObserver = repository.updateBill(BillsDataFactory.makeBill()).test()
        testObserver.assertComplete()
    }


    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubCreateBill(completable: Completable) {
        whenever(cacheDataStore.createBill(any()))
                .thenReturn(completable)
    }

    private fun stubCreateBills(completable: Completable) {
        whenever(cacheDataStore.createBills(any()))
                .thenReturn(completable)
    }

    private fun stubUpdateBill(completable: Completable) {
        whenever(cacheDataStore.updateBill(any()))
                .thenReturn(completable)
    }

    private fun stubGetBills(observable: Flowable<List<BillEntity>>) {
        whenever(cacheDataStore.getBills())
                .thenReturn(observable)
    }

    private fun stubGetBillById(observable: Flowable<BillEntity>) {
        whenever(cacheDataStore.getBillByBillId(any()))
                .thenReturn(observable)
    }

    private fun stubGetBillByIds(observable: Flowable<BillEntity>) {
        whenever(cacheDataStore.getBillByIds(any(), any()))
                .thenReturn(observable)
    }

    private fun stubMapperFromEntity(model: Bill, entity: BillEntity) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

    private fun stubMapperToEntity(model: Bill, entity: BillEntity) {
        whenever(mapper.mapToEntity(model))
                .thenReturn(entity)
    }

}