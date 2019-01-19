package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.*
import com.thomaskioko.paybillmanager.data.executor.JobExecutor
import com.thomaskioko.paybillmanager.domain.executor.ThreadExecutor
import com.thomaskioko.paybillmanager.domain.repository.*
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class DataModule {

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun bindBillsDataRepository(repository: BillsDataRepository): BillsRepository

    @Binds
    abstract fun bindCategoryRepository(repository: CategoryDataRepository): CategoryRepository

    @Binds
    abstract fun bindJengaTokenDataRepository(repository: JengaTokenDataRepository): JengaTokenRepository

    @Binds
    abstract fun bindBillCategoryRepository(repository: BillCategoryDataRepository): BillCategoryRepository

    @Binds
    abstract fun bindMpesaRequestRepository(repository: MpesaPushDataRepository): MpesaRequestRepository
}
