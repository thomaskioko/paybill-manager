package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

open class MpesaPushRemoteDataStore @Inject constructor(
        private val remote: MpesaPushRemote
) : MpesaPushDataStore {

    override fun getMpesaStkPushRequest(mpesaPushRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity> {
        return remote.getMpesaStkPushRequest(mpesaPushRequestEntity)
    }

    override fun saveMpesaPushResponse(mpesaPushResponseEntity: MpesaPushResponseEntity): Completable {
        throw UnsupportedOperationException("Save push response isn't remotely")
    }

    override fun isStkResponseCached(): Single<Boolean> {
        throw UnsupportedOperationException("Is Stk Cached isn't remotely")
    }
}