package com.thomaskioko.paybillmanager.mobile

import android.app.Activity
import android.app.Application
import com.crashlytics.android.Crashlytics
import com.jakewharton.threetenabp.AndroidThreeTen
import com.squareup.leakcanary.LeakCanary
import com.thomaskioko.paybillmanager.mobile.injection.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.fabric.sdk.android.Fabric
import timber.log.Timber
import javax.inject.Inject


class PaybillManagerApp : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        setupTimber()

        AndroidThreeTen.init(this)

        if (!BuildConfig.DEBUG) {
            val fabric = Fabric.Builder(this)
                    .kits(Crashlytics())
                    .debuggable(false)
                    .build()
            Fabric.with(fabric)
        }

        setUpLeakCanary()
    }

    private fun setUpLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) { return }

        LeakCanary.install(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
