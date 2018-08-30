package com.thomaskioko.paybillmanager.mobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.thomaskioko.paybillmanager.mobile.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        // Set up ActionBar
        setSupportActionBar(toolbar_main)
        NavigationUI.setupActionBarWithNavController(this, navController, null)

        val finalHost = NavHostFragment.create(R.navigation.nav_graph)
        supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, finalHost)
                .setPrimaryNavigationFragment(finalHost) // this is the equivalent to app:defaultNavHost="true"
                .commit()

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(null,
                Navigation.findNavController(this, R.id.nav_graph))
    }

}
