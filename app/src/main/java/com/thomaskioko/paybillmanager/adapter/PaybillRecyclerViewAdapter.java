package com.thomaskioko.paybillmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.thomaskioko.paybillmanager.AddPaybillActivity;
import com.thomaskioko.paybillmanager.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Adapter to display paybill.
 *
 * @author Thomas Kioko
 */
public class PaybillRecyclerViewAdapter extends RecyclerView.Adapter<PaybillRecyclerViewAdapter.PaybillHolder> {

    private List<Object> mObjectList;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CELL = 1;
    private Context mContext;

    /**
     * Constructor
     *
     * @param objectList List of Objects
     */
    public PaybillRecyclerViewAdapter(Context context, List<Object> objectList) {
        mObjectList = objectList;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

    @Override
    public int getItemCount() {
        return mObjectList.size();
    }

    @Override
    public PaybillRecyclerViewAdapter.PaybillHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;

        switch (viewType) {
            case TYPE_HEADER: {
                /**
                 * The header view will only be visible when the user has not added any paybills to the
                 */
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_empty_card_big, parent, false);
                return new PaybillHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new PaybillHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(PaybillHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                holder.btnAddBills.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContext.startActivity(new Intent(mContext, AddPaybillActivity.class));
                    }
                });
                break;
            case TYPE_CELL:
                break;
        }
    }


    class PaybillHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.btnAddPayBill)
        Button btnAddBills;

        private PaybillHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}