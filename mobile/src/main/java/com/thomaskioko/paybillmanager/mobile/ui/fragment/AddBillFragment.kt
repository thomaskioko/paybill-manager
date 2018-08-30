package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.thomaskioko.paybillmanager.mobile.R
import kotlinx.android.synthetic.main.fragment_add_bill.*


class AddBillFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_bill, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fab_add_bill_back.setOnClickListener { view ->
            view.findNavController().navigate(R.id.nav_bills_list_fragment)
        }
    }


}
