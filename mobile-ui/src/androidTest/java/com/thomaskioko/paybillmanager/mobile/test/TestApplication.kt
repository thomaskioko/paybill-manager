package com.thomaskioko.paybillmanager.mobile.test

import android.app.Activity
import android.app.Application
import androidx.test.InstrumentationRegistry
import com.thomaskioko.paybillmanager.mobile.injection.DaggerTestApplicationComponent
import com.thomaskioko.paybillmanager.mobile.injection.TestApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class TestApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var injector: DispatchingAndroidInjector<Activity>
    private lateinit var appComponent: TestApplicationComponent

    companion object {
        fun appComponent(): TestApplicationComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext
                    as TestApplication).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerTestApplicationComponent.builder().application(this).build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return injector
    }
}