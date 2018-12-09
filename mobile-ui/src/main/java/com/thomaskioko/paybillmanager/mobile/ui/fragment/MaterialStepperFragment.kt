package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.StepperAdapter
import com.thomaskioko.paybillmanager.mobile.ui.util.AnimationUtils
import com.thomaskioko.paybillmanager.mobile.ui.util.DismissableAnimation
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import kotlinx.android.synthetic.main.fragment_create_bill.*
import timber.log.Timber
import javax.inject.Inject


class MaterialStepperFragment : Fragment(), Injectable, DismissableAnimation, StepperLayout.StepperListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationController: NavigationController


    companion object {
        const val ARG_REVEAL = "args_reveal"
        fun newInstance(revealAnimationSettings: RevealAnimationSettings): MaterialStepperFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_REVEAL, revealAnimationSettings)
            val fragment = MaterialStepperFragment()
            fragment.arguments = bundle
            return fragment
        }
    }




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_create_bill, container, false)

        if (activity!!.intent.hasExtra(ARG_REVEAL)) {
            val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
            AnimationUtils.registerCircularRevealAnimation(view!!,
                    revealAnim,
                    ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                    ContextCompat.getColor(context!!, R.color.white))
        }


        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                navigationController.navigateToBillsListFragment()
                return@OnKeyListener true
            }
            false
        })


        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        stepper_layout.adapter = StepperAdapter(childFragmentManager, activity!!)
        stepper_layout.setListener(this)


        (activity as AppCompatActivity).setSupportActionBar(toolbar_create_bill)

        toolbar_create_bill.setNavigationOnClickListener {
            navigationController.navigateToBillsListFragment()
        }

    }



    override fun onStepSelected(newStepPosition: Int) {
        when (newStepPosition) {
            0 -> toolbar_title.text = resources.getString(R.string.toolbar_new_bill)
            1 -> toolbar_title.text = resources.getString(R.string.toolbar_bill_details)
            2 -> toolbar_title.text = resources.getString(R.string.toolbar_confirm_details)
        }
    }

    override fun onError(verificationError: VerificationError?) {
        Timber.e("onError! -> ${verificationError!!.errorMessage}")
    }

    override fun onReturn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted(completeButton: View?) {
        navigationController.navigateToBillsListFragment()
    }

    override fun dismiss(listener: DismissableAnimation.OnDismissedListener) {
        val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
        AnimationUtils.startCircularExitAnimation(view!!, revealAnim,
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                ContextCompat.getColor(context!!, R.color.white),
                object : DismissableAnimation.OnDismissedListener {
                    override fun onDismissed() {
                        listener.onDismissed()
                    }
                })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            android.R.id.home -> {
                navigationController.navigateToBillsListFragment()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
