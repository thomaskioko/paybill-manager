package com.thomaskioko.paybillmanager.data

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
        private val mapper: MpesaPushResponseMapper
) : MpesaRequestRepository {

    override fun clearMpesaPushRequests(): Completable {
        return factory.retrieveCacheDataStore().clearMpesaPushRequests()
    }

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponse): Completable {
        val mpesaPushResponseEntity = mapper.mapToEntity(mpesaPushResponse)
        return factory.retrieveCacheDataStore().saveMpesaPushResponse(mpesaPushResponseEntity)
    }

    override fun getMpesaStkPush(bearerToken: String, signaturePayload: String): Flowable<MpesaPushResponse> {
        return factory.retrieveRemoteDataStore()
                .getMpesaStkPushRequest(bearerToken, signaturePayload)
                .flatMap {
                    Flowable.just(mapper.mapFromEntity(it))
                }
                .flatMap {
                    saveMpesaPushResponse(it).toSingle { it }.toFlowable()
                }
    }

    override fun getMpesaPushResponse(): Flowable<List<MpesaPushResponse>> {
        return factory.retrieveCacheDataStore()
                .getMpesaStkPushRequests()
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }
    }
}