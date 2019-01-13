package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
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

    override fun clearMpesaPushRequests(): Completable {
        return cache.clearMpesaPushRequests()
    }

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponseEntity): Completable {
        return cache.saveMpesaPushResponse(mpesaPushResponse)
    }

    override fun getMpesaStkPushRequest(mpesaPushRequest: MpesaPushRequest)
            : Flowable<MpesaPushResponseEntity> {
        return cache.getMpesaStkPushRequest(mpesaPushRequest)
    }

    override fun isStkResponseCached(): Single<Boolean> {
        return cache.isStkResponseCached()
    }
}