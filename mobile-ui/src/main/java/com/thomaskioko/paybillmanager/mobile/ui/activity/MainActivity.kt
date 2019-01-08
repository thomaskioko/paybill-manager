package com.thomaskioko.paybillmanager.mobile.ui.activity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.CompoundButton
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
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.SectionDrawerItem
import com.mikepenz.materialdrawer.model.SwitchDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.base.BaseFragmentActivity
import com.thomaskioko.paybillmanager.mobile.util.AppThemes.Companion.INDIGO
import com.thomaskioko.paybillmanager.mobile.util.AppThemes.Companion.PINK
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.CreateCategoryViewModel
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject

class MainActivity : BaseFragmentActivity(), OnCheckedChangeListener {


    @Inject
    lateinit var navigationController: NavigationController

    @Inject
    lateinit var createCategoryViewModel: CreateCategoryViewModel

    private var drawer: Drawer? = null


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createCategoryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CreateCategoryViewModel::class.java)


        setUpDrawer()

        navigationController.navigateToBillsListFragment()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }


    override fun onStart() {
        super.onStart()

        for (category in categoriesList()) {
            createCategoryViewModel.createCategory(category)
        }
    }

    private fun setUpDrawer() {
        val profile = ProfileDrawerItem()
                .withName("Admin")
                .withEmail("admin@app.com")
                .withIcon(AppCompatResources.getDrawable(this, R.drawable.ic_launcher_icon))

        // Create the AccountHeader
        val headerResult = AccountHeaderBuilder()
                .withActivity(this)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.header)
                .withSelectionListEnabled(false)
                .addProfiles(profile)
                .build()

        //Create the drawer
        drawer = DrawerBuilder()
                .withActivity(this)
                .withHasStableIds(true)
                .withDisplayBelowStatusBar(true)
                .withItemAnimator(AlphaCrossFadeAnimator())
                .withAccountHeader(headerResult)
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
                                .withIdentifier(3),
                        SectionDrawerItem()
                                .withName("Settings"),
                        SwitchDrawerItem()
                                .withName("App Theme")
                                .withDescription("Switch between to dark theme")
                                .withIcon(GoogleMaterial.Icon.gmd_color_lens)
                                .withChecked(isThemeDark)
                                .withIdentifier(4)
                                .withOnCheckedChangeListener(this)
                )
                .withOnDrawerItemClickListener { _, _, drawerItem ->
                    if (drawerItem != null) {
                        when (drawerItem.identifier.toInt()) {
                            1 -> {
                                navigationController.navigateToBillsListFragment()
                            }
                        }
                    }
                    false
                }
                .build()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            android.R.id.home -> {
                if (drawer != null && drawer!!.isDrawerOpen) {
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
        closeDrawer()

        if (supportFragmentManager.backStackEntryCount == 0) {
            navigationController.navigateToBillsListFragment()
        }
    }

    private fun closeDrawer() {
        if (drawer != null && drawer!!.isDrawerOpen) {
            drawer!!.closeDrawer()
        } else {
            super.onBackPressed()
        }
    }




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

    override fun onCheckedChanged(drawerItem: IDrawerItem<*, *>?, buttonView: CompoundButton?, isChecked: Boolean) {

        when (drawerItem!!.identifier.toInt()) {
            4 -> {
                if (isChecked) {
                    saveTheme(PINK)
                } else {
                    saveTheme(INDIGO)
                }
                closeDrawer()
            }
        }
    }

}
