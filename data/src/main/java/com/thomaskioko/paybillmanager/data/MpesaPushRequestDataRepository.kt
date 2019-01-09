package com.thomaskioko.paybillmanager.data

import com.thomaskioko.paybillmanager.data.mapper.MpesaPushRequestMapper
import com.thomaskioko.paybillmanager.data.mapper.MpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRequestCache
import com.thomaskioko.paybillmanager.data.store.mpesapush.MpesaPushRequestDataStoreFactory
import com.thomaskioko.paybillmanager.domain.model.MpesaPushResponse
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import com.thomaskioko.paybillmanager.domain.repository.MpesaRequestRepository
import io.reactivex.Flowable
import javax.inject.Inject

class MpesaPushRequestDataRepository @Inject constructor(
        private val mapper: MpesaPushRequestMapper,
        private val responseMapper: MpesaPushResponseMapper,
        private val cache: MpesaPushRequestCache,
        private val factory: MpesaPushRequestDataStoreFactory
) : MpesaRequestRepository {

    override fun createMpesaPushRequest(mpesaPushRequest: MpesaPushRequest): Flowable<MpesaPushResponse> {
        return cache.isMpesaPushResponseCached()
                .flatMapPublisher {
                    factory.getDataStore(it)
                            .createMpesaPushRequest(mapper.mapToEntity(mpesaPushRequest))
                            .distinctUntilChanged()
                }
                .flatMap {
                    factory.getCacheDataStore()
                            .saveMpesaPushResponse(it)
                            .andThen(Flowable.just(it))
                }
                .map {
                    responseMapper.mapFromEntity(it)
                }
    }
}