package com.thomaskioko.paybillmanager.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thomaskioko.paybillmanager.cache.model.Config
import io.reactivex.Single

@Dao
abstract class ConfigDao {

    @Query("SELECT * FROM config")
    abstract fun getConfig(): Single<Config>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertConfig(config: Config)

}