package com.thomaskioko.paybillmanager.ui;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.thomaskioko.paybillmanager.models.PayBill;
import com.thomaskioko.paybillmanager.models.PayBillCategory;
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
public class AddPayBillActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
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
    @Bind(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;

    private static PayBillCategory mPayBillCategory;

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
                actionBar.setTitle("");
            }
        }
    }

    private void initViews() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(AddPayBillActivity.this,
                        android.R.anim.fade_in);
                animation.setDuration(300);

                mLlContainer.startAnimation(animation);
                mLlContainer.setVisibility(View.VISIBLE);

            }
        });

        /**
         * Get all the categories from sugar orm ordering the then using the category name.
         *
         * {@link com.orm.SugarRecord} converts {@link PayBillCategory#categoryName} to category_name.
         *
         * This is the convention followed in Sugar.
         * {@see <href a="http://satyan.github.io/sugar/creation.html"/>}
         */
        List<PayBillCategory> payBillCategoryList = PayBillCategory.findWithQuery(PayBillCategory.class,
                "SELECT * FROM pay_bill_category ORDER BY ? ASC", "category_name");

        /**
         * {@link LayoutManager} is responsible for measuring and positioning item views within a
         * RecyclerView. We then set the layout of the mRecyclerView to gridView using {@link GridLayoutManager}
         */
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 4);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //Set the adapter
        mRecyclerView.setAdapter(new CategoryRecyclerViewAdapter(getApplicationContext(), payBillCategoryList));

    }

    /**
     * Method to save paybill to {@link PayBill}
     */
    @OnClick(R.id.btnSavePayBill)
    void savePaybill() {
        if (isDataValid()) {

            PayBill payBill = new PayBill(mPayBillCategory.getCategoryId(), mEditTextPaybillName.getText().toString(),
                    mEditTextPaybillNumber.getText().toString(), mEditTextAccountNumber.getText().toString());
            payBill.save();

            /**
             * Calling finish() was not reloading the list. So we start a new intent in order to
             * reload the list of paybills after successfully adding
             */

            startActivity(new Intent(getApplicationContext(), MainActivity.class));
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

        //Check if the category is null. If true prompt the user to select a category
        if(mPayBillCategory == null){
            Snackbar snackbar = Snackbar
                    .make(mCoordinatorLayout, "Please select a category", Snackbar.LENGTH_LONG);
            snackbar.show();
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

    /**
     * @return {@link PayBillCategory}
     */
    public PayBillCategory getPaybillCategory() {
        return mPayBillCategory;
    }

    /**
     * @param payBillCategory PayBill category object
     */
    public static void setPaybillCategory(PayBillCategory payBillCategory) {
        mPayBillCategory = payBillCategory;
    }
}
