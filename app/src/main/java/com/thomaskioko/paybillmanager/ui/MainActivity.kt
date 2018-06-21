package com.thomaskioko.paybillmanager.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.thomaskioko.paybillmanager.R

import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class MainActivity :  DaggerAppCompatActivity() , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigationController: NavigationController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationController.navigateToDashboardFragment()
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
