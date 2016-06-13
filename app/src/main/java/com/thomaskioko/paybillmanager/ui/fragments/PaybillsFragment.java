package com.thomaskioko.paybillmanager.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.adapter.PaybillRecyclerViewAdapter;
import com.thomaskioko.paybillmanager.models.Paybill;
import com.thomaskioko.paybillmanager.ui.AddPaybillActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment that allows a user to add paybills and stores them in SQLite.
 *
 * @author Thomas Kioko
 */
public class PaybillsFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.fab)
    FloatingActionButton floatingActionButton;

    private boolean showHeader = false;

    /**
     * Method to instantiate the fragment.
     *
     * @return Fragment instance
     */
    public static PaybillsFragment newInstance() {
        return new PaybillsFragment();
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

        //Fetch paybill items
        List<Paybill> paybillList = Paybill.listAll(Paybill.class);

        if (paybillList.size() == 0) {
            showHeader = true;
            //Hide the floating action button
            floatingActionButton.setVisibility(View.GONE);

            /**
             * We have no paybill records. We create an empty object and add it to the list. This
             * will allow us to display the header view.
             */
            Paybill paybill = new Paybill();
            paybillList.add(paybill);
        }

        RecyclerView.Adapter mAdapter = new PaybillRecyclerViewAdapter(getActivity(), paybillList, showHeader);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * Method to start {@link AddPaybillActivity}
     */
    @OnClick(R.id.fab)
    void startAddPaybillActivity() {
        startActivity(new Intent(getActivity(), AddPaybillActivity.class));
    }
}
