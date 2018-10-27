package com.thomaskioko.paybillmanager.mobile.injection.component

import android.app.Application
import com.thomaskioko.paybillmanager.mobile.PaybillManagerApp
import com.thomaskioko.paybillmanager.mobile.injection.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ApplicationModule::class,
    CacheModule::class,
    DataModule::class,
    PresentationModule::class,
    RemoteModule::class,
    UiModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: PaybillManagerApp)
}
