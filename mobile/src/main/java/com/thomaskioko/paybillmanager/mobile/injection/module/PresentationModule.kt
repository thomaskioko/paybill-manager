package com.thomaskioko.paybillmanager.mobile.injection.module

import androidx.lifecycle.ViewModelProvider
import com.thomaskioko.paybillmanager.mobile.injection.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
@Suppress("unused")
abstract class PresentationModule {


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
