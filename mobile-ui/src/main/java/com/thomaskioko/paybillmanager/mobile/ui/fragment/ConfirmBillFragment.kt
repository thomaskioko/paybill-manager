package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_confirm_bill.*
import org.threeten.bp.OffsetDateTime
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@SuppressLint("VisibleForTests")
class ConfirmBillFragment : Fragment(), Injectable, Step {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var sharedViewModel: SharedViewModel
    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var amount: String
    private lateinit var categoryId: String
    private lateinit var accountNumber: String
    private lateinit var billName: String
    private lateinit var payBillNumber: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_bill, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(SharedViewModel::class.java)

        sharedViewModel.getAmount().observe(this, Observer {
            amount = it
        })

        sharedViewModel.getCategoryId().observe(this, Observer {
            categoryId = it
        })

        sharedViewModel.getBillName().observe(this, Observer {
            billName = it
            tv_detail_bill_name.text = it
        })
        sharedViewModel.getPayBillNumber().observe(this, Observer {
            payBillNumber = it
            tv_bill_detail_paybill_number.text = it
        })

        sharedViewModel.getBillAccountNumber().observe(this, Observer {
            accountNumber = it
            tv_bill_detail_account_number.text = it
        })

        sharedViewModel.getBillLiveData().observe(this, Observer<Resource<BillView>> { it ->
            it?.let { observeBillView(it) }
        })

    }

    override fun onSelected() {

    }

    override fun verifyStep(): VerificationError? {

        val bill = Bill(
                UUID.randomUUID().toString(),
                billName,
                payBillNumber,
                accountNumber,
                amount,
                categoryId.toInt(),
                OffsetDateTime.now().toEpochSecond()
        )

        sharedViewModel.createBill(bill)

        return null
    }

    override fun onError(verificationError: VerificationError) {
        Timber.e("onError! -> ${verificationError.errorMessage}")
    }

    private fun observeBillView(resource: Resource<BillView>) {
        when (resource.status) {
            ResourceState.ERROR -> {
                Timber.e(resource.message)
            }
            ResourceState.LOADING -> {
            }
            ResourceState.SUCCESS -> {
                navigationController.navigateToBillsListFragment()
            }
        }
    }


}
