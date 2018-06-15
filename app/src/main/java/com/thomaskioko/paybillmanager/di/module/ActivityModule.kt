package com.thomaskioko.paybillmanager.di.module

import com.thomaskioko.paybillmanager.ui.IntroActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeIntroActivity(): IntroActivity

}