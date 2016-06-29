package com.thomaskioko.paybillmanager.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.util.NumberFormatUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
    @Bind(R.id.dataLayout)
    RelativeLayout mRelativeLayout;
    private Animation mAnimationKeyboardPop;
    private String mPaybillName, mPaybillNumber, mAccountNumber;
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

        mPaybillName = getIntent().getStringExtra("payBillName");
        mPaybillNumber = getIntent().getStringExtra("payBillNumber");
        mAccountNumber = getIntent().getStringExtra("accountNumber");

        //Setup the actionbar
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.toolbar_title_amount, mPaybillName, mPaybillNumber));
            actionBar.setSubtitle("Acc No - " + getIntent().getStringExtra("accountNumber"));
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
        if (mTvAmount.getText().toString().equals("0.00")) {
            mTvAmount.setText("");
        }
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

    @OnClick({R.id.buttonPayNow, R.id.buttonCancel})
    void buttonClickListener(View view) {
        switch (view.getId()) {
            case R.id.buttonPayNow:
                if (!mTvAmount.getText().toString().equals("0.00")) {
                    showPaymentDialog();
                } else {
                    Snackbar snackbar = Snackbar
                            .make(mRelativeLayout, "Please enter an amount", Snackbar.LENGTH_LONG);
                    snackbar.show();
                }
                break;
            case R.id.buttonCancel:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            default:
                break;
        }
    }

    /**
     * Method to display Alert dialog.
     */
    private void showPaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(AmountActivity.this);
        builder.setIcon(ContextCompat.getDrawable(this, R.mipmap.ic_launcher));
        builder.setTitle(getString(R.string.dialog_title));
        builder.setMessage(getString(R.string.dialog_message,
                NumberFormatUtils.formatAmount(mTvAmount.getText().toString()),
                mPaybillName, mPaybillNumber, mAccountNumber));

        String positiveText = getString(android.R.string.ok);
        builder.setPositiveButton(positiveText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO:: INVOKE API
                    }
                });

        String negativeText = getString(android.R.string.cancel);
        builder.setNegativeButton(negativeText,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        AlertDialog dialog = builder.create();
        // display dialog
        dialog.show();
    }
}
