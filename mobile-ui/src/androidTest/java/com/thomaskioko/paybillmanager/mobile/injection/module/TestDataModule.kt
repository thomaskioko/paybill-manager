package com.thomaskioko.paybillmanager.mobile.injection.module

import com.nhaarman.mockitokotlin2.mock
import com.thomaskioko.paybillmanager.data.executor.JobExecutor
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.repository.*
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class TestDataModule {

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Module
    companion object {
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

        @Provides
        @JvmStatic
        fun providesMpesaRequestRepository(): MpesaRequestRepository {
            return mock()
        }
    }


}