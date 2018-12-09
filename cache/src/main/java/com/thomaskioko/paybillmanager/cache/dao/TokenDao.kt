package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedToken
import io.reactivex.Flowable

@Dao
abstract class TokenDao {

    @Query("SELECT * FROM token")
    @JvmSuppressWildcards
    abstract fun getToken(): Flowable<CachedToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertCachedToken(cachedToken: CachedToken)

    @Query("DELETE FROM token")
    abstract fun deleteCachedToken()

}