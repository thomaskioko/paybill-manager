package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class MpesaPushCacheDataStore @Inject constructor(
        private val cache: MpesaPushCache
) : MpesaPushDataStore {

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponseEntity): Completable {
        return cache.saveMpesaPushResponse(mpesaPushResponse)
    }

    override fun getMpesaStkPushRequest(mpesaPushRequest: MpesaPushRequestEntity)
            : Flowable<MpesaPushResponseEntity> {
        return cache.getMpesaStkPushRequest(mpesaPushRequest)
    }

    override fun isStkResponseCached(): Single<Boolean> {
        return cache.isStkResponseCached()
    }
}