package com.thomaskioko.paybillmanager.mobile.injection.module

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.BillsDataRepository
import com.thomaskioko.paybillmanager.data.CategoryDataRepository
import com.thomaskioko.paybillmanager.data.TokenDataRepository
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.TokenRepository
import dagger.Module
import dagger.Provides

@Module
object TestDataModule {

    @Provides
    @JvmStatic
    fun providesTokenDataRepository(): TokenRepository {
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
}