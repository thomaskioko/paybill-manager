package com.thomaskioko.paybillmanager.data.test

import com.nhaarman.mockitokotlin2.*
import com.thomaskioko.paybillmanager.data.MpesaPushDataRepository
import com.thomaskioko.paybillmanager.data.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushCacheDataStore
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushDataStoreFactory
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushRemoteDataStore
import com.thomaskioko.paybillmanager.data.test.factory.DataFactory
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MpesaPushDataRepositoryTest {


    private var dataStoreFactory = mock<MpesaPushDataStoreFactory>()
    private var mapper = mock<MpesaPushResponseMapper>()
    private var cacheDataStore = mock<MpesaPushCacheDataStore>()
    private var remoteDataStore = mock<MpesaPushRemoteDataStore>()

    private var dataRepository = MpesaPushDataRepository(dataStoreFactory, mapper)

    @Before
    fun setUp() {
        stubDataStoreFactoryGetCacheDataStore()
        stubDataStoreFactoryGetRemoteDataStore()

        val mpesaPushResponse = DataFactory.makeMpesaPushResponse()
        val mpesaPushResponseEntity = DataFactory.makeMpesaPushResponseEntity()

        stubMpesaPushResponseMapperFromEntity(mpesaPushResponseEntity, mpesaPushResponse)
        stubMpesaPushResponseMapper(mpesaPushResponse, mpesaPushResponseEntity)
    }

    @Test
    fun clearMpesaPushRequestsCompletes() {
        stubClearMpesaPushRequests(Completable.complete())
        val testObserver = dataRepository.clearMpesaPushRequests().test()
        testObserver.assertComplete()
    }

    @Test
    fun clearMpesaPushRequestsCallsCacheDataStore() {
        stubClearMpesaPushRequests(Completable.complete())
        dataRepository.clearMpesaPushRequests().test()
        verify(cacheDataStore).clearMpesaPushRequests()
    }

    @Test
    fun clearMpesaPushRequestsNeverCallsRemoteDataStore() {
        stubClearMpesaPushRequests(Completable.complete())
        dataRepository.clearMpesaPushRequests().test()
        verify(remoteDataStore, never()).clearMpesaPushRequests()
    }

    @Test
    fun saveMpesaPushResponseCompletes() {
        stubCacheSaveMpesaPushResponse(Completable.complete())
        val testObserver = dataRepository.saveMpesaPushResponse(
                DataFactory.makeMpesaPushResponse()
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun saveMpesaPushResponseCallsCacheDataStore() {

        stubCacheSaveMpesaPushResponse(Completable.complete())
        dataRepository.saveMpesaPushResponse(DataFactory.makeMpesaPushResponse()).test()
        verify(cacheDataStore).saveMpesaPushResponse(any())
    }

    @Test
    fun saveMpesaPushResponsesNeverCallsRemoteDataStore() {
        stubMpesaPushResponseMapper(
                DataFactory.makeMpesaPushResponse(),
                DataFactory.makeMpesaPushResponseEntity()
        )
        stubCacheSaveMpesaPushResponse(Completable.complete())
        dataRepository.saveMpesaPushResponse(DataFactory.makeMpesaPushResponse()).test()
        verify(remoteDataStore, never()).saveMpesaPushResponse(any())
    }

    @Test
    fun getMpesaStkPushCompletes() {

        stubIsStkResponseCached(Single.just(true))
        stubDataStoreFactoryRetrieveDataStore(cacheDataStore)
        stubCacheGetMpesaStkPushRequest(Flowable.just(DataFactory.makeMpesaPushResponseEntity()))

        stubCacheSaveMpesaPushResponse(Completable.complete())

        val testObserver = dataRepository.getMpesaStkPush("Bearer: ", "signaturePayload").test()
        testObserver.assertComplete()
    }

    @Test
    fun getMpesaStkPushReturnsData() {

        val mpesaPushResponse = DataFactory.makeMpesaPushResponse()
        val mpesaPushResponseEntity = DataFactory.makeMpesaPushResponseEntity()

        stubIsStkResponseCached(Single.just(true))
        stubDataStoreFactoryRetrieveDataStore(cacheDataStore)
        stubCacheGetMpesaStkPushRequest(Flowable.just(mpesaPushResponseEntity))
        stubCacheSaveMpesaPushResponse(Completable.complete())

        val testObserver = dataRepository.getMpesaStkPush("Bearer: ", "signaturePayload").test()
        testObserver.assertValue(mpesaPushResponse)
    }

    @Test
    fun getMpesaPushResponseCompletes() {

        val mpesaPushResponseEntity = listOf(DataFactory.makeMpesaPushResponseEntity())

        stubDataStoreFactoryRetrieveDataStore(cacheDataStore)
        stubCacheMpesaStkPushRequests(Flowable.just(mpesaPushResponseEntity))
        stubCacheSaveMpesaPushResponse(Completable.complete())

        val testObserver = dataRepository.getMpesaPushResponse().test()
        testObserver.assertComplete()
    }

    @Test
    fun getMpesaPushResponseReturnsData() {

        val mpesaPushResponse = listOf(DataFactory.makeMpesaPushResponse())
        val mpesaPushResponseEntity = listOf(DataFactory.makeMpesaPushResponseEntity())

        stubDataStoreFactoryRetrieveDataStore(cacheDataStore)
        stubCacheMpesaStkPushRequests(Flowable.just(mpesaPushResponseEntity))
        stubCacheSaveMpesaPushResponse(Completable.complete())

        val testObserver = dataRepository.getMpesaPushResponse().test()
        testObserver.assertValue(mpesaPushResponse)
    }

    @Test
    fun getMpesaStkPushSavesMpesaPushResponseWhenFromCacheDataStore() {

        val mpesaPushResponse = DataFactory.makeMpesaPushResponse()

        stubDataStoreFactoryRetrieveDataStore(cacheDataStore)
        stubCacheSaveMpesaPushResponse(Completable.complete())

        dataRepository.saveMpesaPushResponse(mpesaPushResponse).test()
        verify(cacheDataStore).saveMpesaPushResponse(any())
    }

    @Test
    fun getMpesaNeverSavesMpesaPushResponseWhenFromRemoteDataStore() {

        val mpesaPushResponse = DataFactory.makeMpesaPushResponse()
        val mpesaPushResponseEntity = DataFactory.makeMpesaPushResponseEntity()

        stubDataStoreFactoryRetrieveDataStore(remoteDataStore)
        stubMpesaPushResponseMapperFromEntity(mpesaPushResponseEntity, mpesaPushResponse)
        stubMpesaPushResponseMapper(mpesaPushResponse, mpesaPushResponseEntity)
        stubCacheSaveMpesaPushResponse(Completable.complete())

        dataRepository.saveMpesaPushResponse(mpesaPushResponse).test()
        verify(remoteDataStore, never()).saveMpesaPushResponse(any())
    }

    private fun stubCacheSaveMpesaPushResponse(completable: Completable) {
        whenever(cacheDataStore.saveMpesaPushResponse(any()))
                .thenReturn(completable)
    }

    private fun stubDataStoreFactoryGetCacheDataStore() {
        whenever(dataStoreFactory.retrieveCacheDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubDataStoreFactoryGetRemoteDataStore() {
        whenever(dataStoreFactory.retrieveRemoteDataStore())
                .thenReturn(cacheDataStore)
    }

    private fun stubIsStkResponseCached(single: Single<Boolean>) {
        whenever(cacheDataStore.isStkResponseCached(any()))
                .thenReturn(single)
    }

    private fun stubCacheGetMpesaStkPushRequest(flowable: Flowable<MpesaPushResponseEntity>) {
        whenever(cacheDataStore.getMpesaStkPushRequest(any(), any()))
                .thenReturn(flowable)
    }

    private fun stubCacheMpesaStkPushRequests(flowable: Flowable<List<MpesaPushResponseEntity>>) {
        whenever(cacheDataStore.getMpesaStkPushRequests())
                .thenReturn(flowable)
    }

    private fun stubDataStoreFactoryRetrieveDataStore(dataStore: MpesaPushDataStore) {
        whenever(dataStoreFactory.retrieveDataStore(any()))
                .thenReturn(dataStore)
    }

    private fun stubClearMpesaPushRequests(completable: Completable) {
        whenever(cacheDataStore.clearMpesaPushRequests())
                .thenReturn(completable)
    }

    private fun stubMpesaPushResponseMapper(model: MpesaPushResponse, entity: MpesaPushResponseEntity) {
        whenever(mapper.mapToEntity(model))
                .thenReturn(entity)
    }

    private fun stubMpesaPushResponseMapperFromEntity(entity: MpesaPushResponseEntity, model: MpesaPushResponse) {
        whenever(mapper.mapFromEntity(entity))
                .thenReturn(model)
    }

}