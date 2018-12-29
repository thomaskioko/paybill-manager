package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.data.BillCategoryDataRepository
import com.thomaskioko.paybillmanager.data.BillsDataRepository
import com.thomaskioko.paybillmanager.data.CategoryDataRepository
import com.thomaskioko.paybillmanager.data.JengaTokenDataRepository
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class DataModule {

    @Binds
    abstract fun bindBillsDataRepository(billsDataRepository: BillsDataRepository): BillsRepository

    @Binds
    abstract fun bindCategoryRepository(categoryRepository: CategoryDataRepository): CategoryRepository

    @Binds
    abstract fun bindJengaTokenDataRepository(jengaTokenDataRepository: JengaTokenDataRepository): JengaTokenRepository

    @Binds
    abstract fun bindBillCategoryRepository(billCategoryDataRepository: BillCategoryDataRepository): BillCategoryRepository
}
