package com.thomaskioko.paybillmanager.mobile.injection.module

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import dagger.Module
import dagger.Provides

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    fun providesTokenDataRepository(): JengaTokenRepository {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesBillsDataRepository(): BillsRepository {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesCategoryRepository(): CategoryRepository {
        return mock()
    }

    @Provides
    @JvmStatic
    fun providesBillCategoryRepository(): BillCategoryRepository {
        return mock()
    }
}