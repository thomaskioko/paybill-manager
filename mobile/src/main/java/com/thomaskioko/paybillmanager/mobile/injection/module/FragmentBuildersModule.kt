package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.mobile.ui.fragment.AddBillFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.BillDetailFragment
import com.thomaskioko.paybillmanager.mobile.ui.fragment.BillsListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBillsListFragment(): BillsListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddBillFragment(): AddBillFragment

    @ContributesAndroidInjector
    abstract fun contributeBillDetailFragment(): BillDetailFragment

}