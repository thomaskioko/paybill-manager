package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stepstone.stepper.BlockingStep
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.domain.model.Bill
import com.thomaskioko.paybillmanager.domain.model.BillCategory
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.ui.base.BaseFragment
import com.thomaskioko.paybillmanager.mobile.util.NumberFormatter.formatNumber
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.billcategory.CreateBillCategoryViewModel
import kotlinx.android.synthetic.main.fragment_confirm_bill.*
import org.threeten.bp.OffsetDateTime
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@SuppressLint("VisibleForTests")
class ConfirmBillFragment : BaseFragment(), BlockingStep {


    @Inject
    lateinit var sharedViewModel: SharedViewModel

    @Inject
    lateinit var createBillCategoryViewModel: CreateBillCategoryViewModel

    private lateinit var amount: String
    private lateinit var categoryId: String
    private lateinit var accountNumber: String
    private lateinit var billName: String
    private lateinit var payBillNumber: String

    override fun getLayoutId(): Int {
        return R.layout.fragment_confirm_bill
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        sharedViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(SharedViewModel::class.java)

        createBillCategoryViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                .get(CreateBillCategoryViewModel::class.java)

        sharedViewModel.getAmount().observe(this, Observer {
            amount = it
            tv_bill_detail_amount.text = formatNumber(it)
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

    }

    override fun onSelected() {

    }

    override fun verifyStep(): VerificationError? {
        return null
    }

    override fun onBackClicked(callback: StepperLayout.OnBackClickedCallback) {
       callback.goToPrevStep()
    }

    override fun onCompleteClicked(callback: StepperLayout.OnCompleteClickedCallback) {

        val bill = Bill(
                UUID.randomUUID().toString(),
                billName,
                payBillNumber,
                accountNumber,
                amount,
                categoryId,
                OffsetDateTime.now().toEpochSecond()
        )

        sharedViewModel.createBill(bill)

        createBillCategoryViewModel.createBillCategory(
                BillCategory(bill.billId, bill.categoryId)
        )
        callback.complete()
    }

    override fun onNextClicked(callback: StepperLayout.OnNextClickedCallback) {

    }

    override fun onError(verificationError: VerificationError) {
        showTopErrorNotification(verificationError.errorMessage)
    }


}
