package com.thomaskioko.paybillmanager.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
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
import com.thomaskioko.paybillmanager.models.Paybill;
import com.thomaskioko.paybillmanager.models.PaybillCategory;
import com.thomaskioko.paybillmanager.ui.AddPaybillActivity;
import com.thomaskioko.paybillmanager.ui.AmountActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display paybill.
 *
 * @author Thomas Kioko
 */
public class PaybillRecyclerViewAdapter extends RecyclerView.Adapter<PaybillRecyclerViewAdapter.PaybillHolder> {

    private List<Paybill> mPaybillList;
    private Context mContext;
    private boolean mShowHeaderView;

    /**
     * Constructor
     *
     * @param context        Application context
     * @param paybillList    List of paybill objects
     * @param showHeaderView {@link Boolean} Whether to display the header or not
     */
    public PaybillRecyclerViewAdapter(Context context, List<Paybill> paybillList, boolean showHeaderView) {
        mPaybillList = paybillList;
        mContext = context;
        mShowHeaderView = showHeaderView;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mPaybillList.size();
    }

    @Override
    public PaybillRecyclerViewAdapter.PaybillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the item view.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_paybill_card, parent, false);
        return new PaybillHolder(view);

    }

    @Override
    public void onBindViewHolder(final PaybillHolder holder, int position) {

        Paybill paybill = mPaybillList.get(position);

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

                        mContext.startActivity(new Intent(mContext, AddPaybillActivity.class), options.toBundle());
                    } else {
                        mContext.startActivity(new Intent(mContext, AddPaybillActivity.class));
                    }
                }
            });
        } else {
            holder.emptyLayout.setVisibility(View.GONE);

            List<PaybillCategory> paybillCategoryList = PaybillCategory.findWithQuery(PaybillCategory.class,
                    "SELECT * FROM paybill_category WHERE category_id = ? ", paybill.getCategoryId());
            for (PaybillCategory paybillCategory : paybillCategoryList) {
                holder.tvCategoryName.setText(paybillCategory.getCategoryName());

                int resourceId = 0;
                //TODO:: Load the drawable using the drawable id. This will do away with the switch case

                switch (paybillCategory.getCategoryName()) {
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

            holder.tvPaybillName.setText(paybill.getPaybillName());
            holder.tvAccountNumber.setText(mContext.getResources()
                    .getString(R.string.placeholder_account_number, paybill.getPaybillAccountNumber()));
            holder.ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PopupMenu popupMenu = new PopupMenu(v.getContext(), holder.ivEdit);
                    popupMenu.inflate(R.menu.menu_paybill_options);
                    popupMenu.setGravity(GravityCompat.END);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.menu_item_edit:
                                    ///TODO:: Edit the record
                                    return true;
                                case R.id.menu_item_delete:
                                    //TODO:: Delete the record
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
                    mContext.startActivity(new Intent(mContext, AmountActivity.class));
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