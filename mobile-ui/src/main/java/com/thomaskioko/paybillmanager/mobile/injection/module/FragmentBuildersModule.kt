package com.thomaskioko.paybillmanager.mobile.injection.module

import com.thomaskioko.paybillmanager.mobile.ui.fragment.*
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeBillsListFragment(): BillsListFragment

    @ContributesAndroidInjector
    abstract fun contributeAddBillFragment(): BillAmountFragment

    @ContributesAndroidInjector
    abstract fun contributeBillDetailFragment(): BillDetailFragment

    @ContributesAndroidInjector
    abstract fun contributeBillDetailsBottomDialogFragment(): BillDetailsFragment

    @ContributesAndroidInjector
    abstract fun contributeMaterialStepperFragment(): MaterialStepperFragment

    @ContributesAndroidInjector
    abstract fun contributeConfirmBillFragment(): ConfirmBillFragment

}
