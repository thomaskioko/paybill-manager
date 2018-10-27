package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.BillsDataRepository
import com.thomaskioko.paybillmanager.data.CategoryDataRepository
import com.thomaskioko.paybillmanager.data.TokenDataRepository
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.TokenRepository
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class DataModule {

    @Binds
    abstract fun bindTokenDataRepository(tokenDataRepository: TokenDataRepository): TokenRepository

    @Binds
    abstract fun bindBillsDataRepository(billsDataRepository: BillsDataRepository): BillsRepository

    @Binds
    abstract fun bindCategoryRepository(categoryRepository: CategoryDataRepository): CategoryRepository
}
