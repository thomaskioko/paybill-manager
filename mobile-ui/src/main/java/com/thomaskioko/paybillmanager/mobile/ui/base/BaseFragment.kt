package com.thomaskioko.paybillmanager.mobile.ui.base

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.fragment.MaterialStepperFragment
import com.thomaskioko.paybillmanager.mobile.ui.util.AnimationUtils
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import javax.inject.Inject

abstract class BaseFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigationController: NavigationController


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(getLayoutId(), container, false)

        if (activity!!.intent.hasExtra(MaterialStepperFragment.ARG_REVEAL)) {
            val revealAnim: RevealAnimationSettings = arguments?.getParcelable(MaterialStepperFragment.ARG_REVEAL)!!
            AnimationUtils.registerCircularRevealAnimation(view!!,
                    revealAnim,
                    ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                    ContextCompat.getColor(context!!, R.color.white))
        }

        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                closeKeyboard()
                navigationController.navigateToBillsListFragment()
                return@OnKeyListener true
            }
            false
        })

        return view
    }

    abstract fun getLayoutId(): Int

    fun closeKeyboard() {
        val view = activity!!.currentFocus
        if (view != null) {
            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm!!.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}