package com.thomaskioko.paybillmanager.cache

import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.cache.mapper.CachedMpesaPushResponseMapper
import com.thomaskioko.paybillmanager.data.model.MpesaPushResponseEntity
import com.thomaskioko.paybillmanager.data.repository.mpesapush.MpesaPushCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MpesaPushResponseCacheImpl @Inject constructor(
        private val database: PayBillManagerDatabase,
        private val mapper: CachedMpesaPushResponseMapper
) : MpesaPushCache {

    override fun clearMpesaPushRequests(): Completable {
        return Completable.defer {
            database.mpesaPushResponseDao().deleteCachedMpesaPushResponse()
            Completable.complete()
        }
    }

    override fun getMpesaStkPushRequests(): Flowable<List<MpesaPushResponseEntity>> {
        return Flowable.defer {
            database.mpesaPushResponseDao().getMpesaPushResponses()
        }.map {
            it.map { mapper.mapFromCached(it) }
        }
    }

    override fun saveMpesaPushResponse(mpesaPusRequestEntity: MpesaPushResponseEntity): Completable {
        return Completable.defer {
            database.mpesaPushResponseDao().insertMpesaPushResponse(
                    mapper.mapToCached(mpesaPusRequestEntity)
            )
            Completable.complete()
        }
    }

    override fun isStkResponseCached(transactionReference: String): Single<Boolean> {
        return database.mpesaPushResponseDao()
                .getMpesaPushResponseByTransactionRef(transactionReference).isEmpty.map { !it }
    }

}