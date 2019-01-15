package com.thomaskioko.paybillmanager.data.store.mpesapush

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

    override fun getMpesaStkPushRequests(): Flowable<List<MpesaPushResponseEntity>> {
        throw UnsupportedOperationException("Get response isn't remotely")
    }

    override fun clearMpesaPushRequests(): Completable {
        throw UnsupportedOperationException("Save push response isn't remotely")
    }

    override fun getMpesaStkPushRequest(bearerToken: String, signaturePayload: String): Flowable<MpesaPushResponseEntity> {
        return remote.getMpesaStkPushRequest(bearerToken, signaturePayload)
    }

    override fun saveMpesaPushResponse(mpesaPushResponse: MpesaPushResponseEntity): Completable {
        throw UnsupportedOperationException("Save push response isn't remotely")
    }

    override fun isStkResponseCached(transactionReference: String): Single<Boolean> {
        throw UnsupportedOperationException("Is Stk Cached isn't remotely")
    }
}