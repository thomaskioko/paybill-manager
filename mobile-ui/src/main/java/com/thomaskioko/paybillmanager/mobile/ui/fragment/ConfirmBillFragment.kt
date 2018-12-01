package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import timber.log.Timber


class ConfirmBillFragment : Fragment(), Injectable, Step {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_confirm_bill, container, false)
    }

    override fun onSelected() {

    }

    override fun verifyStep(): VerificationError? {
       return null
    }

    override fun onError(verificationError: VerificationError) {
        Timber.e("onError! -> ${verificationError.errorMessage}")
    }


}
