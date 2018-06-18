package com.thomaskioko.paybillmanager.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter

import com.thomaskioko.paybillmanager.R
import com.thomaskioko.paybillmanager.ui.fragment.UpcomingBillsFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.github.florent37.materialviewpager.MaterialViewPager
import com.github.florent37.materialviewpager.header.HeaderDesign
import kotlinx.android.synthetic.main.header_logo.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = material_view_pager.toolbar
        if (toolbar != null) {
            toolbar.title = ""
            setSupportActionBar(toolbar)

            //Set up the actionBar
            val actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(false)
        }

        material_view_pager.viewPager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when (position % 2) {
                    0 -> return UpcomingBillsFragment()
                    1 -> return UpcomingBillsFragment()
                    else -> return UpcomingBillsFragment()
                }
            }

            /**
             * Method to get the number of tabs
             * @return Number of tabs
             */
            override fun getCount(): Int {
                return 2
            }

            /**
             * Method to set the title of the tabs
             * @param position Tab position
             * @return Tab title
             */
            override fun getPageTitle(position: Int): CharSequence? {
                when (position % 2) {
                    0 -> return "Upcoming Bills"
                    1 -> return "Recent Transactions"
                }
                return ""
            }
        }

        //Setup the header
        material_view_pager.setMaterialViewPagerListener(MaterialViewPager.Listener { page ->
            when (page) {
                0 -> {
                    header_logo.setImageDrawable(ResourcesCompat.getDrawable(resources,
                            R.drawable.ic_dollar, null))
                    return@Listener HeaderDesign.fromColorResAndUrl(
                            R.color.colorPrimary,
                            "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg")
                }
                1 -> {
                    header_logo.setImageDrawable(ResourcesCompat.getDrawable(resources,
                            R.drawable.ic_dollar, null))
                    return@Listener HeaderDesign.fromColorResAndUrl(
                            R.color.colorAccent,
                            "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg")
                }
            }

            null
        })

        material_view_pager.viewPager.offscreenPageLimit = material_view_pager.viewPager.adapter?.count!!
        material_view_pager.pagerTitleStrip.setViewPager(material_view_pager.viewPager)
    }
}
