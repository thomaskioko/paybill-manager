package com.thomaskioko.paybillmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomaskioko.paybillmanager.R;

/**
 * Fragment that displays user transaction history.
 *
 * @author Thomas Kioko
 */
public class TransactionHistoryFragment extends Fragment {


    public TransactionHistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_history, container, false);
    }

}
