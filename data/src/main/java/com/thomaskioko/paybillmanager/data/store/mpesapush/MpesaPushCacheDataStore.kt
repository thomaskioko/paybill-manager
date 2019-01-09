package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRequestCache
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class MpesaPushCacheDataStore @Inject constructor(
        private val cache: MpesaPushRequestCache
) : MpesaPushDataStore {

    override fun saveMpesaPushResponse(mpesaPushResponseEntity: MpesaPushResponseEntity): Completable {
       return cache.saveMpesaRequestResponse(mpesaPushResponseEntity)
    }

    override fun createMpesaPushRequest(mpesaPushRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity>{
        return cache.saveMpesaPushRequest(mpesaPushRequestEntity)
    }
}