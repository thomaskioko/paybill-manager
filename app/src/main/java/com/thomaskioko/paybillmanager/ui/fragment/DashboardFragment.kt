package com.thomaskioko.paybillmanager.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.thomaskioko.paybillmanager.R
import com.thomaskioko.paybillmanager.di.Injectable
import com.thomaskioko.paybillmanager.ui.NavigationController
import kotlinx.android.synthetic.main.fragment_dashboard.*
import javax.inject.Inject


class DashboardFragment : Fragment(), Injectable {


    @Inject
    lateinit var navigationController: NavigationController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_add_bill.setOnClickListener {

            navigationController.navigateToAddBillFragment()
        }

    }
}
