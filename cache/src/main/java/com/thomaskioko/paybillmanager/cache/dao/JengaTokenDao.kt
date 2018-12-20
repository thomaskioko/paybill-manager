package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.CachedJengaToken
import io.reactivex.Flowable

@Dao
abstract class JengaTokenDao {

    @Query("SELECT * FROM jenga_token")
    @JvmSuppressWildcards
    abstract fun getToken(): Flowable<CachedJengaToken>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertCachedToken(cachedJengaToken: CachedJengaToken)

    @Query("DELETE FROM jenga_token")
    abstract fun deleteCachedToken()

}