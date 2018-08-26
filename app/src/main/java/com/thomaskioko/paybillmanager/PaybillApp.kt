package com.thomaskioko.paybillmanager

import android.app.Activity
import android.app.Application
import com.thomaskioko.paybillmanager.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class PaybillApp : Application(), HasActivityInjector {

    @Inject
    lateinit var calligraphyConfig: CalligraphyConfig

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)

        CalligraphyConfig.initDefault(calligraphyConfig)
    }

    override fun activityInjector() = dispatchingAndroidInjector

}
