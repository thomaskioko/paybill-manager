package com.thomaskioko.paybillmanager.mobile.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.mikepenz.fontawesome_typeface_library.FontAwesome
import com.mikepenz.google_material_typeface_library.GoogleMaterial
import com.mikepenz.itemanimators.AlphaCrossFadeAnimator
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.CreateCategoryViewModel
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
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var createCategoryViewModel: CreateCategoryViewModel

    private var drawer: Drawer? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navigationController.navigateToBillsListFragment()


        createCategoryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CreateCategoryViewModel::class.java)
        for (category in categoriesList()) {
            createCategoryViewModel.createCategory(category)
        }

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


    private fun categoriesList(): List<Category> {
        val categories: MutableList<Category> = mutableListOf()

        categories.add(Category("1", "Baby", R.drawable.ic_baby_buggy_24dp))
        categories.add(Category("2", "Education", R.drawable.ic_education_24dp))
        categories.add(Category("3", "Fitness", R.drawable.ic_fitness_center_24dp))
        categories.add(Category("4", "Food & Drinks", R.drawable.ic_local_dining_24dp))
        categories.add(Category("5", "Fuel", R.drawable.ic_fuel_24dp))
        categories.add(Category("6", "Health", R.drawable.ic_health_24dp))
        categories.add(Category("7", "House", R.drawable.ic_house_24dp))
        categories.add(Category("8", "Internet", R.drawable.ic_wifi_24dp))
        categories.add(Category("9", "Pets", R.drawable.ic_pets_24dp))
        categories.add(Category("10", "Shopping", R.drawable.ic_shopping_cart_24dp))
        categories.add(Category("11", "Transportation", R.drawable.ic_transport_24dp))
        categories.add(Category("12", "Travelling", R.drawable.ic_travel_24dp))

        return categories
    }

}
