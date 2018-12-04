package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.showErrorMessage
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.DaysAdapter
import com.thomaskioko.paybillmanager.mobile.util.DateUtils
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_bottom_dialog.*
import org.threeten.bp.OffsetDateTime
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class BillDetailsFragment : Fragment(), Injectable, DaysAdapter.OnRecyclerViewItemClickListener,
        Step {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var sharedViewModel: SharedViewModel
    @Inject
    lateinit var navigationController: NavigationController
    private lateinit var amount: String
    private lateinit var categoryId: String


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_dialog, container, false)
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

        sharedViewModel.getBillLiveData().observe(this, Observer<Resource<BillView>> { it ->
            it?.let { observeBillView(it) }
        })


        val adapter = DaysAdapter(this)

        recycler_view_dates.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recycler_view_dates.adapter = adapter

        adapter.offsetDateTimeLists = DateUtils.getDaysOfWeek()
        adapter.notifyDataSetChanged()

    }

    override fun onSelected() {

    }

    override fun verifyStep(): VerificationError? {

        return when {
            et_bill_name.text!!.isEmpty() -> {
                input_layout_bill_name.showErrorMessage(resources.getString(R.string.error_no_name))
                VerificationError(resources.getString(R.string.error_no_name))
            }
            et_bill_number.text!!.isEmpty() -> {
                input_layout_bill_number.showErrorMessage(resources.getString(R.string.error_no_pay_bill_number))
                VerificationError(resources.getString(R.string.error_no_pay_bill_number))
            }
            et_account_number.text!!.isEmpty() -> {
                input_layout_account_number.showErrorMessage(resources.getString(R.string.error_no_account_number))
                VerificationError(resources.getString(R.string.error_no_account_number))
            }
            else -> {
                val bill = Bill(
                        UUID.randomUUID().toString(),
                        et_bill_name.text.toString(),
                        et_bill_number.text.toString(),
                        et_account_number.text.toString(),
                        amount,
                        categoryId.toInt(),
                        OffsetDateTime.now().toEpochSecond()
                )

//                sharedViewModel.createBill(bill)
                closeKeyboard()
                null
            }
        }
    }

    private fun closeKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    override fun onError(error: VerificationError) {
        Timber.e("onError! -> ${error.errorMessage}")
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


    override fun selectedDateItem(offsetDateTime: OffsetDateTime) {
    }
}
