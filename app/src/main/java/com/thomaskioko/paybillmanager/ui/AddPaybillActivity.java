package com.thomaskioko.paybillmanager.ui;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.thomaskioko.paybillmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Activity to allow users to add paybills.
 *
 * @author Thomas Kioko
 */
public class AddPaybillActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paybill);
        ButterKnife.bind(this);

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            //Set up the actionBar
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

}
