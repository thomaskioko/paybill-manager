package com.thomaskioko.paybillmanager.data.store.mpesapush

import com.thomaskioko.paybillmanager.data.model.MpesaPushRequestEntity
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushDataStore
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushRequestRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class MpesaPushRemoteDataStore @Inject constructor(
        private val remote: MpesaPushRequestRemote
) : MpesaPushDataStore {


    override fun saveMpesaPushResponse(mpesaPushResponseEntity: MpesaPushResponseEntity): Completable {
        throw UnsupportedOperationException("clear token isn't supported here...")
    }

    override fun createMpesaPushRequest(mpesaPushRequestEntity: MpesaPushRequestEntity): Flowable<MpesaPushResponseEntity> {
        return remote.createMpesaPushRequest(mpesaPushRequestEntity)
    }
}