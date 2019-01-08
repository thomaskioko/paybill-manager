package com.thomaskioko.paybillmanager.mobile.injection

import android.app.Application
import com.thomaskioko.paybillmanager.domain.repository.BillCategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.BillsRepository
import com.thomaskioko.paybillmanager.domain.repository.CategoryRepository
import com.thomaskioko.paybillmanager.domain.repository.JengaTokenRepository
import com.thomaskioko.paybillmanager.mobile.TestApplication
import com.thomaskioko.paybillmanager.mobile.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestApplicationModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    PresentationModule::class,
    UiModule::class,
    TestRemoteModule::class
])
interface TestApplicationComponent {

    fun categoryRepository(): CategoryRepository

    fun billsBillsRepository(): BillsRepository

    fun jengaTokenRepository(): JengaTokenRepository

    fun billCategoryRepository(): BillCategoryRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun inject(application: TestApplication)

}