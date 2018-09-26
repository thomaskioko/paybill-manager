package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import kotlinx.android.synthetic.main.fragment_bills_list.*
import javax.inject.Inject

class BillsListFragment : Fragment(), Injectable {

    @Inject
    lateinit var navigationController: NavigationController



    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_bills_list, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as AppCompatActivity).setSupportActionBar(toolbar_bill_list)

        (activity as AppCompatActivity).supportActionBar!!.setHomeButtonEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity).supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_drawer_white)


        fab_add_bill.setOnClickListener {
            navigationController.navigateToAddBillFragment(constructRevealSettings())
        }

    }

    private fun constructRevealSettings(): RevealAnimationSettings {
        val fabX = (fab_add_bill.x + fab_add_bill.width / 2).toInt()
        val fabY = (fab_add_bill.y + fab_add_bill.height / 2).toInt()
        val containerW = bills_container.width
        val containerH = bills_container.height
        return RevealAnimationSettings(fabX, fabY, containerW, containerH)
    }
}
