package com.thomaskioko.paybillmanager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.thomaskioko.paybillmanager.fragments.PaybillsFragment;
import com.thomaskioko.paybillmanager.fragments.RecyclerViewFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Main activity
 *
 * @author Thomas Kioko
 */
public class MainActivity extends AppCompatActivity {

    @Bind(R.id.materialViewPager)
    MaterialViewPager mMaterialViewPager;
    @Bind(R.id.headerLogo)
    ImageView mHeaderLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = mMaterialViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            //Set up the actionBar
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(false);
            }
        }

        setupMaterialViewPager();
    }

    /**
     * Method to set-up MaterialViewPager
     */
    private void setupMaterialViewPager() {
        mMaterialViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position % 3) {
                    case 0:
                        return PaybillsFragment.newInstance();
                    case 1:
                        return RecyclerViewFragment.newInstance();
                    default:
                        return RecyclerViewFragment.newInstance();
                }
            }

            /**
             * Method to get the number of tabs
             * @return Number of tabs
             */
            @Override
            public int getCount() {
                return 2;
            }

            /**
             * Method to set the title of the tabs
             * @param position Tab position
             * @return Tab title
             */
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 3) {
                    case 0:
                        return getResources().getString(R.string.tab_title_paybills);
                    case 1:
                        return getResources().getString(R.string.tab_title_transaction_history);
                }
                return "";
            }
        });

        //Setup the header
        mMaterialViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        mHeaderLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                                R.mipmap.ic_monetization_on_white_48dp, null));
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        mHeaderLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                                R.mipmap.ic_receipt_white_48dp, null));
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                }

                return null;
            }
        });

        mMaterialViewPager.getViewPager().setOffscreenPageLimit(mMaterialViewPager.getViewPager().getAdapter().getCount());
        mMaterialViewPager.getPagerTitleStrip().setViewPager(mMaterialViewPager.getViewPager());
    }
}
