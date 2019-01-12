package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.MpesaPushRequestMapper
import com.thomaskioko.paybillmanager.data.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.domain.repository.MpesaRequestRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class MpesaPushDataRepository @Inject constructor(
        private val factory: MpesaPushDataStoreFactory,
        private val mapper: MpesaPushRequestMapper,
        private val responseMapper: MpesaPushResponseMapper
) : MpesaRequestRepository {

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponse): Completable {
        val mpesaRepositoryEntity = responseMapper.mapToEntity(mpesaPushResponse)
        return factory.retrieveCacheDataStore().saveMpesaPushResponse(mpesaRepositoryEntity)
    }

    override fun getMpesaStkPush(mpesaPushRequest: MpesaPushRequest): Flowable<MpesaPushResponse> {
        return factory.retrieveCacheDataStore()
                .isStkResponseCached()
                .flatMapPublisher {
                    factory.getDataStore(it)
                            .getMpesaStkPushRequest(mapper.mapToEntity(mpesaPushRequest))
                }
                .flatMap {
                    Flowable.just(responseMapper.mapFromEntity(it))
                }
                .flatMap {
                   saveMpesaPushResponse(it).toSingle { it }.toFlowable()
                }
    }
}