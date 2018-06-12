package com.thomaskioko.paybillmanager.di.component

import android.app.Application
import com.thomaskioko.paybillmanager.PaybillApp
import com.thomaskioko.paybillmanager.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            AndroidInjectionModule::class,
            AppModule::class
        ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(paybillApp: PaybillApp)
}
