package com.thomaskioko.paybillmanager.mobile.ui.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.thomaskioko.paybillmanager.domain.model.JengaToken
import com.thomaskioko.paybillmanager.mobile.BuildConfig

import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.hide
import com.thomaskioko.paybillmanager.mobile.extension.show
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.presentation.model.JengaTokenView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.JengaRequestsViewModel
import kotlinx.android.synthetic.main.fragment_bill_detail.*
import timber.log.Timber
import javax.inject.Inject

class BillDetailFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var jengaRequestsViewModel: JengaRequestsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        jengaRequestsViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(JengaRequestsViewModel::class.java)

        jengaRequestsViewModel.getJengaToken().observe(this, Observer<Resource<JengaTokenView>>{
            it?.let {
                handleData(it)
            }
        })

    }

    override fun onStart() {
        super.onStart()

        jengaRequestsViewModel.fetchJengaToken()
    }


    private fun handleData(resource: Resource<JengaTokenView>){
        when(resource.status){
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
