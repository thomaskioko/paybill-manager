package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushCache
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.domain.model.mpesa.MpesaPushRequest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class MpesaPushCacheDataStore @Inject constructor(
        private val cache: MpesaPushCache
) : MpesaPushDataStore {

    override fun getMpesaStkPushRequests(): Flowable<List<MpesaPushResponseEntity>> {
        return cache.getMpesaStkPushRequests()
    }

    override fun clearMpesaPushRequests(): Completable {
        return cache.clearMpesaPushRequests()
    }

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponseEntity): Completable {
        return cache.saveMpesaPushResponse(mpesaPushResponse)
    }

    override fun getMpesaStkPushRequest(mpesaPushRequest: MpesaPushRequest): Flowable<MpesaPushResponseEntity> {
        throw UnsupportedOperationException("Save push response isn't remotely")
    }

    override fun isStkResponseCached(transactionReference: String): Single<Boolean> {
        return cache.isStkResponseCached(transactionReference)
    }
}