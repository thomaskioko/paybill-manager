package com.thomaskioko.paybillmanager.mobile.injection.module

import android.app.Application
import com.thomaskioko.paybillmanager.cache.BillCategoryCacheImpl
import com.thomaskioko.paybillmanager.cache.BillsCacheImpl
import com.thomaskioko.paybillmanager.cache.CategoryCacheImpl
import com.thomaskioko.paybillmanager.cache.JengaTokenCacheImpl
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import com.thomaskioko.paybillmanager.data.repository.bills.BillsCache
import com.thomaskioko.paybillmanager.data.repository.category.CategoryCache
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
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
    abstract fun bindBillsCache(cacheImpl: BillsCacheImpl): BillsCache

    @Binds
    abstract fun bindCategoryCache(cacheImpl: CategoryCacheImpl): CategoryCache

    @Binds
    abstract fun bindJengaTokenCache(cacheImpl: JengaTokenCacheImpl): JengaTokenCache

    @Binds
    abstract fun bindBillCategoryCache(cacheImpl: BillCategoryCacheImpl): BillCategoryCache
}
