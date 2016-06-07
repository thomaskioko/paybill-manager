package com.thomaskioko.paybillmanager.ui.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.thomaskioko.paybillmanager.R;
import com.thomaskioko.paybillmanager.adapter.PaybillRecyclerViewAdapter;
import com.thomaskioko.paybillmanager.models.PaybillCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that allows a user to add paybills and stores them in SQLite.
 *
 * @author Thomas Kioko
 */
public class PaybillsFragment extends Fragment {

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<Object> mContentItems = new ArrayList<>();

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

        RecyclerView.LayoutManager layoutManager;

        layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        RecyclerView.Adapter mAdapter = new PaybillRecyclerViewAdapter(getActivity(), mContentItems);

        mRecyclerView.setAdapter(mAdapter);

        //Add object to {@link #mContentItems}
        for (int i = 0; i < 1; ++i) {
            mContentItems.add(new Object());
        }
        mAdapter.notifyDataSetChanged();
    }
}
