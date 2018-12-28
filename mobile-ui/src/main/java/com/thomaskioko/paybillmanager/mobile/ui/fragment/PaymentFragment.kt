package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.hide
import com.thomaskioko.paybillmanager.mobile.extension.show
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.JengaRequestsViewModel
import kotlinx.android.synthetic.main.fragment_payment.*
import timber.log.Timber
import javax.inject.Inject

class PaymentFragment : BottomSheetDialogFragment(), Injectable, View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var jengaRequestsViewModel: JengaRequestsViewModel

    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        jengaRequestsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(JengaRequestsViewModel::class.java)

        jengaRequestsViewModel.getJengaToken().observe(this, Observer<Resource<JengaTokenView>> {
            it?.let {
                handleData(it)
            }
        })

        iv_mobile_money.setOnClickListener(this)
        iv_card.setOnClickListener(this)

        btn_complete_payment.setOnClickListener {
            jengaRequestsViewModel.fetchJengaToken()
        }

    }


    override fun onClick(view: View) {
        val mobileMoney = resources.getDrawable(R.drawable.ic_phone_24dp)
        val creditCard = resources.getDrawable(R.drawable.ic_credit_card_24dp)

        when (view.id) {
            R.id.iv_mobile_money -> {

                input_layout_phone_number.show()

                mobileMoney.setTint(resources.getColor(R.color.lightPrimaryDark))
                creditCard.setTint(resources.getColor(R.color.greyAccent))

                iv_mobile_money.setImageResource(R.drawable.ic_phone_24dp)
                iv_card.setImageResource(R.drawable.ic_credit_card_24dp)

                iv_mobile_money.setBackgroundDrawable(
                        resources.getDrawable(R.drawable.background_rectangle_activated))
                iv_card.setBackgroundDrawable(
                        resources.getDrawable(R.drawable.background_rectangle_deactivated))

            }
            R.id.iv_card -> {
                input_layout_phone_number.hide()

                mobileMoney.setTint(resources.getColor(R.color.greyAccent))
                creditCard.setTint(resources.getColor(R.color.lightPrimaryDark))

                iv_mobile_money.setImageResource(R.drawable.ic_phone_24dp)
                iv_card.setImageResource(R.drawable.ic_credit_card_24dp)

                iv_card.setBackgroundDrawable(
                        resources.getDrawable(R.drawable.background_rectangle_activated))
                iv_mobile_money.setBackgroundDrawable(
                        resources.getDrawable(R.drawable.background_rectangle_deactivated))
            }
        }
    }


    private fun handleData(resource: Resource<JengaTokenView>) {
        when (resource.status) {
            ResourceState.LOADING -> {
                progress_bar.show()
            }
            ResourceState.SUCCESS -> {
                progress_bar.hide()
                Timber.d("@getJengaToken ${resource.data.toString()}")
            }
            ResourceState.ERROR -> {
                progress_bar.hide()
                Toast.makeText(activity!!, resource.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
