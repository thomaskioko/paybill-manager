package com.thomaskioko.paybillmanager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomaskioko.paybillmanager.R;

/**
 * Fragment that allows a user to add paybills and stores them in SQLite.
 *
 * @author Thomas Kioko
 */
public class PaybillsFragment extends Fragment {

    public static PaybillsFragment newInstance() {
        return new PaybillsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_paybills, container, false);
    }

}
