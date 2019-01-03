package com.thomaskioko.paybillmanager.mobile.injection.module

import android.app.Application
import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.cache.db.PayBillManagerDatabase
import com.thomaskioko.paybillmanager.data.repository.billcategory.BillCategoryCache
import com.thomaskioko.paybillmanager.data.repository.bills.BillsCache
import com.thomaskioko.paybillmanager.data.repository.category.CategoryCache
import com.thomaskioko.paybillmanager.data.repository.jengatoken.JengaTokenCache
import dagger.Module
import dagger.Provides


@Module
object TestCacheModule {


    @Provides
    @JvmStatic
    fun providesDataBase(application: Application): PayBillManagerDatabase {
        return PayBillManagerDatabase.getInstance(application)
    }


    @Provides
    @JvmStatic
    fun providesJengaTokenCache(): JengaTokenCache {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesBillsCache(): BillsCache {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesCategoryCache(): CategoryCache {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesBillCategoryCache(): BillCategoryCache {
        return mock()
    }

}