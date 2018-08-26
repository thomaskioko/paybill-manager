package com.thomaskioko.paybillmanager.di.module

import com.thomaskioko.paybillmanager.ui.fragment.DashboardFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeDashboardFragment(): DashboardFragment

}