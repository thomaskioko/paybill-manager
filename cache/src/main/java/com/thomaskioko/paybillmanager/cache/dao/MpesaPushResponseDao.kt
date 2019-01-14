package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedMpesaPushResponse
import io.reactivex.Flowable

@Dao
abstract class MpesaPushResponseDao {

    @Query("SELECT * FROM mpesa_response")
    @JvmSuppressWildcards
    abstract fun getMpesaPushResponses(): Flowable<List<CachedMpesaPushResponse>>

    @Query("SELECT * FROM mpesa_response where status = :status")
    @JvmSuppressWildcards
    abstract fun getMpesaPushResponseByStatus(status: String): Flowable<List<CachedMpesaPushResponse>>

    @Query("SELECT * FROM mpesa_response where transactionRef = :transactionReference")
    @JvmSuppressWildcards
    abstract fun getMpesaPushResponseByTransactionRef(transactionReference: String)
            : Flowable<CachedMpesaPushResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertMpesaPushResponse(cachedMpesaPushResponse: CachedMpesaPushResponse)

    @Query("DELETE FROM mpesa_response")
    abstract fun deleteCachedMpesaPushResponse()

}