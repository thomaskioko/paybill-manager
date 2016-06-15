package com.thomaskioko.paybillmanager.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.adapter.CategoryRecyclerViewAdapter;
import com.thomaskioko.paybillmanager.models.Paybill;
import com.thomaskioko.paybillmanager.models.PaybillCategory;
import com.thomaskioko.paybillmanager.util.AnimationTransitionUtils;
import com.thomaskioko.paybillmanager.util.OnRevealAnimationListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Activity to allow users to add paybills.
 *
 * @author Thomas Kioko
 */
public class AddPaybillActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.btnSavePayBill)
    Button mButtonSavePaybill;
    @Bind(R.id.etPaybillName)
    EditText mEditTextPaybillName;
    @Bind(R.id.editTextPaybillNumber)
    EditText mEditTextPaybillNumber;
    @Bind(R.id.editTextAccountNumber)
    EditText mEditTextAccountNumber;
    @Bind(R.id.activity_contact_rl_container)
    RelativeLayout mRlContainer;
    @Bind(R.id.activity_contact_ll_container)
    LinearLayout mLlContainer;
    @Bind(R.id.activity_contact_fab)
    FloatingActionButton mFab;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_paybill);
        ButterKnife.bind(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setupEnterAnimation();
            setupExitAnimation();
        } else {
            initViews();
        }

        if (toolbar != null) {
            setSupportActionBar(toolbar);

            //Set up the actionBar
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    private void initViews() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(AddPaybillActivity.this,
                        android.R.anim.fade_in);
                animation.setDuration(300);

                mLlContainer.startAnimation(animation);
                mLlContainer.setVisibility(View.VISIBLE);

            }
        });

        /**
         * Get all the categories from sugar orm ordering the then using the category name.
         *
         * {@link com.orm.SugarRecord} converts {@link PaybillCategory#categoryName} to category_name.
         *
         * This is the convention followed in Sugar.
         * {@see <href a="http://satyan.github.io/sugar/creation.html"/>}
         */
        List<PaybillCategory> paybillCategoryList = PaybillCategory.findWithQuery(PaybillCategory.class,
                "SELECT * FROM paybill_category ORDER BY ? ASC", "category_name");

        /**
         * {@link LayoutManager} is responsible for measuring and positioning item views within a
         * RecyclerView. We then set the layout of the recyclerView to gridView using {@link GridLayoutManager}
         */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new CategoryRecyclerViewAdapter(getApplicationContext(), paybillCategoryList));

    }

    /**
     * Method to save paybill to {@link com.thomaskioko.paybillmanager.models.Paybill}
     */
    @OnClick(R.id.btnSavePayBill)
    void savePaybill() {
        if (isDataValid()) {
            //TODO:: Get the selected category id
            Paybill paybill = new Paybill("1", mEditTextPaybillName.getText().toString(),
                    mEditTextPaybillNumber.getText().toString(), mEditTextAccountNumber.getText().toString());
            paybill.save();

            finish();
        }
    }

    /**
     * Method to check if any of the fields are empty
     *
     * @return {@link Boolean} True/False
     */
    private boolean isDataValid() {

        if (mEditTextPaybillNumber.getText().toString().equals("")) {
            mEditTextPaybillNumber.requestFocus();
            mEditTextPaybillNumber.setError(getString(R.string.error_message_paybill_number));
            return false;
        }

        if (mEditTextAccountNumber.getText().toString().equals("")) {
            mEditTextAccountNumber.requestFocus();
            mEditTextAccountNumber.setError(getString(R.string.error_message_account_number));
            return false;
        }

        if (mEditTextPaybillName.getText().toString().equals("")) {
            mEditTextPaybillName.requestFocus();
            mEditTextPaybillName.setError(getString(R.string.error_message_paybill_name));
            return false;
        }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                /**
                 * Override the toolbar back setting. When clicked, we want to show the display the
                 * animation.
                 */
                AnimationTransitionUtils.animateRevealHide(this, mRlContainer, R.color.white,
                        mFab.getWidth() / 2,
                        new OnRevealAnimationListener() {
                            @Override
                            public void onRevealHide() {
                                backPressed();
                            }

                            @Override
                            public void onRevealShow() {

                            }
                        });
                break;
        }
        return true;
    }

    /**
     * Method to setup the enter animation.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupEnterAnimation() {
        Transition transition = TransitionInflater.from(this)
                .inflateTransition(R.transition.changebounds_with_arcmotion);
        getWindow().setSharedElementEnterTransition(transition);
        transition.addListener(new Transition.TransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {

            }

            @Override
            public void onTransitionEnd(Transition transition) {
                transition.removeListener(this);
                animateRevealShow(mRlContainer);
                mFab.setVisibility(View.GONE);
            }

            @Override
            public void onTransitionCancel(Transition transition) {

            }

            @Override
            public void onTransitionPause(Transition transition) {

            }

            @Override
            public void onTransitionResume(Transition transition) {

            }
        });
    }

    /**
     * Method to animate the view passed
     *
     * @param viewRoot {@link View}
     */
    private void animateRevealShow(final View viewRoot) {
        int cx = (viewRoot.getLeft() + viewRoot.getRight()) / 2;
        int cy = (viewRoot.getTop() + viewRoot.getBottom()) / 2;

        AnimationTransitionUtils.animateRevealShow(this, mRlContainer,
                mFab.getWidth() / 2, R.color.colorAccent,
                cx, cy, new OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {

                    }

                    @Override
                    public void onRevealShow() {
                        initViews();
                    }
                });
    }


    @Override
    public void onBackPressed() {
        AnimationTransitionUtils.animateRevealHide(this, mRlContainer, R.color.white,
                mFab.getWidth() / 2,
                new OnRevealAnimationListener() {
                    @Override
                    public void onRevealHide() {
                        backPressed();
                    }

                    @Override
                    public void onRevealShow() {

                    }
                });
    }

    /**
     * This method calls the back button action
     */
    private void backPressed() {
        super.onBackPressed();
    }

    /**
     * Method to setup the exit animation
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setupExitAnimation() {
        Fade fade = new Fade();
        getWindow().setReturnTransition(fade);
        fade.setDuration(getResources().getInteger(R.integer.animation_duration));
    }

}
