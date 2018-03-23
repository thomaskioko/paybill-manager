package com.thomaskioko.paybillmanager.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.ui.fragments.PayBillsFragment;
import com.thomaskioko.paybillmanager.ui.fragments.RecyclerViewFragment;

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
                switch (position % 2) {
                    case 0:
                        return PayBillsFragment.newInstance();
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
                switch (position % 2) {
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
                        changeStatusBarColor(getResources().getColor(R.color.paybillsFragmentDark));
                        mHeaderLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                                R.mipmap.ic_monetization_on_white_48dp, null));
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.paybillsFragmentLight,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg"
                        );
                    case 1:
                        changeStatusBarColor(getResources().getColor(R.color.transactionFragmentDark));
                        mHeaderLogo.setImageDrawable(ResourcesCompat.getDrawable(getResources(),
                                R.mipmap.ic_receipt_white_48dp, null));
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.transactionFragmentLight,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg"
                        );
                }

                return null;
            }
        });

        mMaterialViewPager.getViewPager().setOffscreenPageLimit(mMaterialViewPager.getViewPager().getAdapter().getCount());
        mMaterialViewPager.getPagerTitleStrip().setViewPager(mMaterialViewPager.getViewPager());
    }

    /**
     * Helper method to change the color of the status bar.
     *
     * @param color color value
     */
    private void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            // finally change the color
            window.setStatusBarColor(color);
        }
    }
}
