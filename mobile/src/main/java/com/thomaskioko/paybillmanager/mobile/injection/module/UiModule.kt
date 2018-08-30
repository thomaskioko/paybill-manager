package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.domain.executor.PostExecutionThread
import com.thomaskioko.paybillmanager.mobile.ui.MainActivity
import com.thomaskioko.paybillmanager.mobile.ui.SplashScreenActivity
import com.thomaskioko.paybillmanager.mobile.util.UiThread
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
@Suppress("unused")
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesSplashScreenActivity(): SplashScreenActivity

}