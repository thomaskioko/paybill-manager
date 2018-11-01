package com.thomaskioko.paybillmanager.mobile

import android.app.Activity
import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.thomaskioko.paybillmanager.mobile.injection.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric



class PaybillManagerApp : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        setupTimber()

        AndroidThreeTen.init(this)

        val fabric = Fabric.Builder(this)
                .kits(Crashlytics())
                .debuggable(false)
                .build()
        Fabric.with(fabric)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
