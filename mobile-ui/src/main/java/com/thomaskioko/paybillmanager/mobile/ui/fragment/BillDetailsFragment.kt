package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.showErrorMessage
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.base.BaseFragment
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.dateToTimeStamp
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.formatTimeStampToDate
import com.thomaskioko.paybillmanager.mobile.util.DateUtils.getMonthFromTimeStamp
import com.thomaskioko.paybillmanager.presentation.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.fragment_bill_details.*
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@SuppressLint("VisibleForTests")
class BillDetailsFragment : BaseFragment(), DatePickerDialog.OnDateSetListener, Step {


    @Inject
    lateinit var sharedViewModel: SharedViewModel
    private lateinit var amount: String
    private lateinit var categoryId: String


    override fun getLayoutId(): Int {
        return R.layout.fragment_bill_details
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

        val calender = Calendar.getInstance()
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        tv_payment_date.setOnClickListener {
            val datePickerDialog = DatePickerDialog(activity!!, R.style.DatePickerDialogTheme, this, year, month, day)
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()
            datePickerDialog.setCancelable(false)
            datePickerDialog.show()
            datePickerDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setBackgroundColor(resources.getColor(R.color.transparent))
            datePickerDialog.getButton(DialogInterface.BUTTON_POSITIVE).setBackgroundColor(resources.getColor(R.color.transparent))

        }


    }

    override fun onDateSet(datePicker: DatePicker?, year: Int, month: Int, dayOfTheMonth: Int) {

        val calender = Calendar.getInstance()
        val hours = calender.time.hours
        val minutes = calender.time.minutes
        val seconds = calender.time.seconds

        val selectedDate = "${month + 1}-$dayOfTheMonth-$year $hours:$minutes:$seconds"
        val timeStamp = dateToTimeStamp(selectedDate)

        cb_reminder.text = resources.getString(R.string.placeholder_repeat, getMonthFromTimeStamp(timeStamp))

        tv_payment_date.text = formatTimeStampToDate(timeStamp)

    }

    override fun onSelected() {

    }

    @SuppressLint("VisibleForTests")
    override fun verifyStep(): VerificationError? {

        return when {
            et_bill_name.text!!.isEmpty() -> {
                input_layout_bill_name.showErrorMessage(resources.getString(R.string.error_no_name))
                input_layout_bill_name.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shake_error))
                VerificationError(resources.getString(R.string.error_no_name))
            }
            et_bill_number.text!!.isEmpty() -> {
                input_layout_bill_number.showErrorMessage(resources.getString(R.string.error_no_pay_bill_number))
                input_layout_bill_number.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shake_error))
                VerificationError(resources.getString(R.string.error_no_pay_bill_number))
            }
            et_account_number.text!!.isEmpty() -> {
                input_layout_account_number.showErrorMessage(resources.getString(R.string.error_no_account_number))
                input_layout_account_number.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.shake_error))
                VerificationError(resources.getString(R.string.error_no_account_number))
            }
            else -> {
                closeKeyboard()
                sharedViewModel.setBillName(et_bill_name.text.toString())
                sharedViewModel.setBillAccountNumber(et_bill_number.text.toString())
                sharedViewModel.setPayBillNumber(et_account_number.text.toString())
                null
            }
        }
    }


    override fun onError(error: VerificationError) {
        Timber.e("onError! -> ${error.errorMessage}")
    }


}
