package com.thomaskioko.paybillmanager.mobile.injection.module

import android.app.Application
import com.thomaskioko.paybillmanager.cache.BillsCacheImpl
import com.thomaskioko.paybillmanager.cache.TokenCacheImpl
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.data.repository.bills.BillsCache
import com.thomaskioko.paybillmanager.data.repository.token.TokenCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
@Suppress("unused")
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: Application): PayBillManagerDatabase {
            return PayBillManagerDatabase.getInstance(application)
        }
    }

    @Binds
    abstract fun bindTokenCache(tokenCacheImpl: TokenCacheImpl): TokenCache

    @Binds
    abstract fun bindBillsCache(billsCacheImpl: BillsCacheImpl): BillsCache
}