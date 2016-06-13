package com.thomaskioko.paybillmanager.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.adapter.CategoryRecyclerViewAdapter;
import com.thomaskioko.paybillmanager.models.Paybill;
import com.thomaskioko.paybillmanager.models.PaybillCategory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

}
