package com.thomaskioko.paybillmanager.mobile.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.thomaskioko.paybillmanager.mobile.R
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var navigationController: NavigationController

    private var drawer: Drawer? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationController.navigateToBillsListFragment()

        setUpDrawer()
    }


    private fun setUpDrawer() {
        val profile = ProfileDrawerItem()
                .withName("")
                .withEmail("")
                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_launcher_icon))

        // Create the AccountHeader
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .addProfiles(profile)
                .withSelectionListEnabled(false)
                .build()

        //Create the drawer
        drawer = DrawerBuilder()
                .withActivity(this)
                .withHasStableIds(true)
                .withDisplayBelowStatusBar(true)
                .withItemAnimator(AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        PrimaryDrawerItem()
                                .withName("Home")
                                .withDescription("View upcoming bills")
                                .withIcon(FontAwesome.Icon.faw_home)
                                .withIdentifier(1),
                        PrimaryDrawerItem()
                                .withName("Dashboard")
                                .withDescription("Summary of transactions")
                                .withIcon(GoogleMaterial.Icon.gmd_account_circle)
                                .withIdentifier(2),
                        PrimaryDrawerItem()
                                .withName("History")
                                .withDescription("View previous transactions")
                                .withIcon(FontAwesome.Icon.faw_gamepad)
                                .withIdentifier(3)
                )
                .withOnDrawerItemClickListener { _, _, drawerItem ->
                    if (drawerItem != null) {
                        Timber.d("Implement Navigation")
                    }
                    false
                }
                .build()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> if (drawer != null) {
                if (drawer!!.isDrawerOpen) {
                    drawer!!.closeDrawer()
                } else {
                    drawer!!.openDrawer()
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (drawer != null && drawer!!.isDrawerOpen) {
            drawer!!.closeDrawer()
        } else {
            super.onBackPressed()
        }

        if (supportFragmentManager.backStackEntryCount == 0) {
            navigationController.navigateToBillsListFragment()
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector

}
