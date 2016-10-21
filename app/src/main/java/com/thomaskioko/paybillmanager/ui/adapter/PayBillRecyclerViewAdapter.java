package com.thomaskioko.paybillmanager.ui.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.models.PayBill;
import com.thomaskioko.paybillmanager.models.PayBillCategory;
import com.thomaskioko.paybillmanager.ui.AddPayBillActivity;
import com.thomaskioko.paybillmanager.ui.AmountActivity;
import com.thomaskioko.paybillmanager.ui.fragments.PayBillsFragment;
import com.thomaskioko.paybillmanager.util.ApplicationConstants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display paybill.
 *
 * @author Thomas Kioko
 */
public class PayBillRecyclerViewAdapter extends RecyclerView.Adapter<PayBillRecyclerViewAdapter.PaybillHolder> {

    private List<PayBill> mPayBillList;
    private Context mContext;
    private boolean mShowHeaderView;

    /**
     * Constructor
     *
     * @param context        Application context
     * @param payBillList    List of paybill objects
     * @param showHeaderView {@link Boolean} Whether to display the header or not
     */
    public PayBillRecyclerViewAdapter(Context context, List<PayBill> payBillList, boolean showHeaderView) {
        mPayBillList = payBillList;
        mContext = context;
        mShowHeaderView = showHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPayBillList.size();
    }

    @Override
    public PayBillRecyclerViewAdapter.PaybillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the item view.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_paybill_card, parent, false);
        return new PaybillHolder(view);

    }

    @Override
    public void onBindViewHolder(final PaybillHolder holder, int position) {

        final PayBill[] payBill = {mPayBillList.get(position)};

        /**
         * Check if the header should me displayed and hide/display the relevant view
         */
        if (mShowHeaderView) {
            holder.payBillDetailLayout.setVisibility(View.GONE);

            holder.btnAddBills.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        //Create a transition object and define when the transition should begin from.
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                                (Activity) mContext, holder.btnAddBills,
                                holder.btnAddBills.getTransitionName()
                        );

                        mContext.startActivity(new Intent(mContext, AddPayBillActivity.class), options.toBundle());
                    } else {
                        mContext.startActivity(new Intent(mContext, AddPayBillActivity.class));
                    }
                }
            });
        } else {
            holder.emptyLayout.setVisibility(View.GONE);

            List<PayBillCategory> payBillCategoryList = PayBillCategory.findWithQuery(PayBillCategory.class,
                    "SELECT * FROM pay_bill_category WHERE category_id = ? ", payBill[0].getCategoryId());
            for (PayBillCategory payBillCategory : payBillCategoryList) {
                holder.tvCategoryName.setText(payBillCategory.getCategoryName());

                int resourceId = 0;
                //TODO:: Load the drawable using the drawable id. This will do away with the switch case

                switch (payBillCategory.getCategoryName()) {
                    case "Utilities":
                        resourceId = R.mipmap.ic_utilities;
                        break;
                    case "Entertainment":
                        resourceId = R.mipmap.ic_entertainment;
                        break;
                    case "Internet":
                        resourceId = R.mipmap.ic_internet;
                        break;
                    case "Others":
                        resourceId = R.mipmap.ic_others;
                        break;
                    default:
                        break;
                }

                Glide.with(mContext)
                        .load(resourceId)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.ivCategoryIcon);
            }

            holder.tvPaybillName.setText(payBill[0].getPaybillName());
            holder.tvAccountNumber.setText(mContext.getResources()
                    .getString(R.string.placeholder_account_number, payBill[0].getPaybillAccountNumber()));
            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Display a popup view with two option. Edit and Delete
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.ivEdit);
                    popupMenu.inflate(R.menu.menu_paybill_options);
                    popupMenu.setGravity(GravityCompat.END);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_item_edit:
                                    //Edit the record
                                    Intent editPayBillIntent = new Intent(mContext, AddPayBillActivity.class);
                                    editPayBillIntent.putExtra("payBillId", payBill[0].getPaybillId());
                                    editPayBillIntent.putExtra("categoryId", payBill[0].getCategoryId());
                                    editPayBillIntent.putExtra("payBillName", payBill[0].getPaybillName());
                                    editPayBillIntent.putExtra("payBillNumber", payBill[0].getPaybillNumber());
                                    editPayBillIntent.putExtra("accountNumber", payBill[0].getPaybillAccountNumber());
                                    mContext.startActivity(new Intent(editPayBillIntent));
                                    return true;
                                case R.id.menu_item_delete:
                                    //Delete the record
                                    payBill[0] = PayBill.findById(PayBill.class, payBill[0].getId());
                                    payBill[0].delete();

                                    /**
                                     * We use a {@link LocalBroadcastManager} to invoke
                                     * {@link PayBillsFragment#loadPayBills()} and reload the views once
                                     * a record is deleted.
                                     */
                                    Intent intent = new Intent(ApplicationConstants.RELOAD_PAY_BILLS);
                                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                                    return true;
                                default:
                                    return false;
                            }
                        }
                    });
                    popupMenu.show();
                }
            });

            holder.view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, AmountActivity.class);
                    intent.putExtra("payBillName", payBill[0].getPaybillName());
                    intent.putExtra("payBillNumber", payBill[0].getPaybillNumber());
                    intent.putExtra("accountNumber", payBill[0].getPaybillAccountNumber());
                    mContext.startActivity(new Intent(intent));
                }
            });
        }
    }

    /**
     * Uses a ViewHolder to describe the item view and metadata about its place within the RecyclerView.
     */
    class PaybillHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.btnAddPayBill)
        Button btnAddBills;
        @Bind(R.id.ivCategoryIcon)
        ImageView ivCategoryIcon;
        @Bind(R.id.ivCategoryEdit)
        ImageView ivEdit;
        @Bind(R.id.tvAccountNumber)
        TextView tvAccountNumber;
        @Bind(R.id.tvPaybillName)
        TextView tvPaybillName;
        @Bind(R.id.tvCategoryName)
        TextView tvCategoryName;
        @Bind(R.id.payBillDetailLayout)
        RelativeLayout payBillDetailLayout;
        @Bind(R.id.emptyLayout)
        RelativeLayout emptyLayout;
        View view;

        /**
         * Constructor
         *
         * @param itemView populated view
         */
        private PaybillHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

    }
}