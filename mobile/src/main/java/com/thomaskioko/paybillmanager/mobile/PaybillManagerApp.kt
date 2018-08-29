package com.thomaskioko.paybillmanager.mobile

import android.app.Activity
import android.app.Application
import com.thomaskioko.paybillmanager.mobile.injection.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class PaybillManagerApp : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        setupTimber()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}