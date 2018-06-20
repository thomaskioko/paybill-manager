package com.thomaskioko.paybillmanager.di.module

import com.thomaskioko.paybillmanager.ui.IntroActivity
import com.thomaskioko.paybillmanager.ui.MainActivity
import com.thomaskioko.paybillmanager.ui.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
@Suppress("unused")
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeIntroActivity(): IntroActivity

    @ContributesAndroidInjector
    abstract fun contributeSplashScreenActivity(): SplashScreenActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity

}