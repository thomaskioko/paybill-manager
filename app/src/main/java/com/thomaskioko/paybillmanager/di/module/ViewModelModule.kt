package com.thomaskioko.paybillmanager.di.module


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thomaskioko.paybillmanager.di.ViewModelKey
import com.thomaskioko.paybillmanager.viewmodel.DashboardViewModel
import com.thomaskioko.paybillmanager.viewmodel.PaybillViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: PaybillViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(dashboardViewModel: DashboardViewModel): ViewModel
}