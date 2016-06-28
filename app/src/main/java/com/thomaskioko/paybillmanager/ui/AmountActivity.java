package com.thomaskioko.paybillmanager.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.thomaskioko.paybillmanager.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This activity 'replaces' the default android keyboard, presenting a custom calculator like
 * keyboard.
 *
 * @author Thomas Kioko
 */
public class AmountActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {

    @Bind(R.id.tvCurrencyAmount)
    TextView mTvAmount;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.buttonPayNow)
    Button mButtonPayNow;
    @Bind(R.id.buttonCancel)
    Button mButtonCancel;
    private Animation mAnimationKeyboardPop;
    /**
     * TextView numbers
     */
    private TextView anti_theft_t9_key_0, anti_theft_t9_key_1, anti_theft_t9_key_2,
            anti_theft_t9_key_3, anti_theft_t9_key_4, anti_theft_t9_key_5, anti_theft_t9_key_6,
            anti_theft_t9_key_7, anti_theft_t9_key_8, anti_theft_t9_key_9,
            anti_theft_t9_key_clear, anti_theft_t9_key_00;
    private static final String LOG_TAG = AmountActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        //Setup the actionbar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getResources().getString(R.string.toolbar_title_amount));
        }

        //Popup animation for when the keyboard is clicked.
        mAnimationKeyboardPop = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.keyboard_button_pop);
        mAnimationKeyboardPop.setAnimationListener(this);

        mTvAmount.setText(getResources().getString(R.string.default_amount));

        anti_theft_t9_key_0 = (TextView) findViewById(R.id.anti_theft_t9_key_0);
        anti_theft_t9_key_1 = (TextView) findViewById(R.id.anti_theft_t9_key_1);
        anti_theft_t9_key_2 = (TextView) findViewById(R.id.anti_theft_t9_key_2);
        anti_theft_t9_key_3 = (TextView) findViewById(R.id.anti_theft_t9_key_3);
        anti_theft_t9_key_4 = (TextView) findViewById(R.id.anti_theft_t9_key_4);
        anti_theft_t9_key_5 = (TextView) findViewById(R.id.anti_theft_t9_key_5);
        anti_theft_t9_key_6 = (TextView) findViewById(R.id.anti_theft_t9_key_6);
        anti_theft_t9_key_7 = (TextView) findViewById(R.id.anti_theft_t9_key_7);
        anti_theft_t9_key_8 = (TextView) findViewById(R.id.anti_theft_t9_key_8);
        anti_theft_t9_key_9 = (TextView) findViewById(R.id.anti_theft_t9_key_9);
        anti_theft_t9_key_00 = (TextView) findViewById(R.id.anti_theft_t9_key_00);
        anti_theft_t9_key_clear = (TextView) findViewById(R.id.anti_theft_t9_key_clear);

        if (anti_theft_t9_key_0 != null) {
            anti_theft_t9_key_0.setOnClickListener(this);
        }
        if (anti_theft_t9_key_1 != null) {
            anti_theft_t9_key_1.setOnClickListener(this);
        }
        if (anti_theft_t9_key_2 != null) {
            anti_theft_t9_key_2.setOnClickListener(this);
        }
        if (anti_theft_t9_key_3 != null) {
            anti_theft_t9_key_3.setOnClickListener(this);
        }
        if (anti_theft_t9_key_4 != null) {
            anti_theft_t9_key_4.setOnClickListener(this);
        }
        if (anti_theft_t9_key_5 != null) {
            anti_theft_t9_key_5.setOnClickListener(this);
        }
        if (anti_theft_t9_key_6 != null) {
            anti_theft_t9_key_6.setOnClickListener(this);
        }
        if (anti_theft_t9_key_7 != null) {
            anti_theft_t9_key_7.setOnClickListener(this);
        }
        if (anti_theft_t9_key_8 != null) {
            anti_theft_t9_key_8.setOnClickListener(this);
        }
        if (anti_theft_t9_key_9 != null) {
            anti_theft_t9_key_9.setOnClickListener(this);
        }
        if (anti_theft_t9_key_clear != null) {
            anti_theft_t9_key_clear.setOnClickListener(this);
        }
        if (anti_theft_t9_key_00 != null) {
            anti_theft_t9_key_00.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        mTvAmount.setText(""); //Clear the text.
        switch (v.getId()) {
            case R.id.anti_theft_t9_key_0:
                anti_theft_t9_key_0.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("0");
                break;
            case R.id.anti_theft_t9_key_1:
                anti_theft_t9_key_1.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("1");
                break;
            case R.id.anti_theft_t9_key_2:
                anti_theft_t9_key_2.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("2");
                break;
            case R.id.anti_theft_t9_key_3:
                anti_theft_t9_key_3.startAnimation(mAnimationKeyboardPop);

                mTvAmount.append("3");
                break;
            case R.id.anti_theft_t9_key_4:
                anti_theft_t9_key_4.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("4");
                break;
            case R.id.anti_theft_t9_key_5:
                anti_theft_t9_key_5.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("5");
                break;
            case R.id.anti_theft_t9_key_6:
                anti_theft_t9_key_6.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("6");
                break;
            case R.id.anti_theft_t9_key_7:
                anti_theft_t9_key_7.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("7");
                break;
            case R.id.anti_theft_t9_key_8:
                anti_theft_t9_key_8.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("8");
                break;
            case R.id.anti_theft_t9_key_9:
                anti_theft_t9_key_9.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append("9");
                break;
            case R.id.anti_theft_t9_key_00:
                anti_theft_t9_key_00.startAnimation(mAnimationKeyboardPop);
                mTvAmount.append(".");
                break;
            case R.id.anti_theft_t9_key_clear: {
                anti_theft_t9_key_clear.startAnimation(mAnimationKeyboardPop);

                String amountString = mTvAmount.getText().toString();

                if (amountString.length() > 0) {
                    String newPasswordStr = new StringBuilder(amountString)
                            .deleteCharAt(amountString.length() - 1).toString();
                    mTvAmount.setText(newPasswordStr);
                }
            }
            break;
        }
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
