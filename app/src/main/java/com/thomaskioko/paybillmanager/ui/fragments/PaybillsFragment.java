package com.thomaskioko.paybillmanager.ui.fragments;


import android.app.ActivityOptions;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.ui.adapter.PayBillRecyclerViewAdapter;
import com.thomaskioko.paybillmanager.models.PayBill;
import com.thomaskioko.paybillmanager.ui.AddPayBillActivity;
import com.thomaskioko.paybillmanager.util.ApplicationConstants;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that allows a user to add paybills and stores them in SQLite.
 *
 * @author Thomas Kioko
 */
public class PayBillsFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab)
    FloatingActionButton mFloatingActionButton;

    private boolean showHeader = false;
    private BroadcastReceiver mRegistrationBroadcastReceiver;

    /**
     * Method to instantiate the fragment.
     *
     * @return Fragment instance
     */
    public static PayBillsFragment newInstance() {
        return new PayBillsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_paybills, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        /**
         * Set the layout of the recyclerView
         */
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

        loadPayBills();

        /**
         * Broadcast receiver called to reload list.
         * */
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // checking for type intent filter
                if (intent.getAction().equals(ApplicationConstants.RELOAD_PAY_BILLS)) {
                    loadPayBills();
                }
            }
        };
    }

    /**
     * Method to load and display PayBills
     */
    private void loadPayBills() {
        //Fetch paybill items
        List<PayBill> payBillList = PayBill.listAll(PayBill.class);

        if (payBillList.size() == 0) {
            showHeader = true;
            //Hide the floating action button
            mFloatingActionButton.setVisibility(View.GONE);

            /**
             * We have no payBill records. We create an empty object and add it to the list. This
             * will allow us to display the header view.
             */
            PayBill payBill = new PayBill();
            payBillList.add(payBill);
        }

        RecyclerView.Adapter mAdapter = new PayBillRecyclerViewAdapter(getActivity(), payBillList, showHeader);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        registerForContextMenu(mRecyclerView);
    }

    /**
     * Method to start {@link AddPayBillActivity}
     */
    @OnClick(R.id.fab)
    void startAddPaybillActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //Create a transition object and define when the transition should begin from.
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                    mFloatingActionButton, mFloatingActionButton.getTransitionName());
            startActivity(new Intent(getActivity(), AddPayBillActivity.class), options.toBundle());
        } else {
            startActivity(new Intent(getActivity(), AddPayBillActivity.class));
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(ApplicationConstants.RELOAD_PAY_BILLS));
    }

    @Override
    public void onPause() {
        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

}
