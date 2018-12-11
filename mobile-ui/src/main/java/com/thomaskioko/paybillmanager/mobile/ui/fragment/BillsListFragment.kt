package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.razerdp.widget.animatedpieview.AnimatedPieViewConfig
import com.razerdp.widget.animatedpieview.data.SimplePieInfo
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.hide
import com.thomaskioko.paybillmanager.mobile.extension.show
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.mapper.BillsViewMapper
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.BillOnClickListener
import com.thomaskioko.paybillmanager.mobile.ui.adapter.BillsAdapter
import com.thomaskioko.paybillmanager.mobile.ui.util.RecyclerViewItemDecoration
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import com.thomaskioko.paybillmanager.presentation.model.BillView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.GetBillsViewModel
import kotlinx.android.synthetic.main.fragment_bills_list.*
import javax.inject.Inject

@SuppressLint("VisibleForTests")
class BillsListFragment : Fragment(), Injectable{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var viewModel: GetBillsViewModel
    @Inject
    lateinit var mapper: BillsViewMapper

    @Inject
    lateinit var adapter: BillsAdapter


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

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GetBillsViewModel::class.java)

        setupBillsRecycler()
        mapper = BillsViewMapper()

        val animatedConfig = AnimatedPieViewConfig()
        animatedConfig.startAngle((-90).toFloat())// Starting angle offset
                .strokeMode(true)// Whether to draw ring-chart(default:true)
                .strokeWidth(35)// Stroke width for ring-chart
                .splitAngle(1.toFloat())// Clearance angle
                .focusAlphaType(AnimatedPieViewConfig.FOCUS_WITH_ALPHA_REV)// Alpha change mode for selected pie
                .interpolator(DecelerateInterpolator())// Set animation interpolator
                .focusAlpha(150)// Alpha for selected pie (depend on focusAlphaType)
                .focusAlphaType(AnimatedPieViewConfig.FOCUS_WITH_ALPHA)
                .addData(SimplePieInfo(30.toDouble(), Color.parseColor("#FFC5FF8C"), "Shopping"))
                .addData(SimplePieInfo(18.0, Color.parseColor("#2E7D32"), "Internet"))
                .addData(SimplePieInfo(18.0, Color.parseColor("#C15D15"), "Internet"))
                .addData(SimplePieInfo(18.0, Color.parseColor("#FFB300"), "Internet"))
                .addData(SimplePieInfo(5.0, Color.parseColor("#6A1B9A"), "Internet"))
                .animatePie(false)
                .duration(200)
        animated_pie_view.applyConfig(animatedConfig)
        animated_pie_view.start()

        fab_add_bill.setOnClickListener {
            navigationController.navigateToMaterialStepperFragment(constructRevealSettings())
        }

    }


    override fun onStart() {
        super.onStart()
        viewModel.getBills().observe(this,
                Observer<Resource<List<BillView>>> { it ->
                    it?.let { observeBillsData(it) }
                })

        viewModel.fetchBills()
    }

    private fun setupBillsRecycler() {
        val divider = RecyclerViewItemDecoration(resources.getColor(R.color.white), 2)
        adapter = BillsAdapter()
        adapter.billOnClickListener = listener
        recycler_view_bill_list.layoutManager = LinearLayoutManager(activity)
        recycler_view_bill_list.addItemDecoration(divider)
        recycler_view_bill_list.adapter = adapter
    }


    private fun observeBillsData(resource: Resource<List<BillView>>) {
        when (resource.status) {
            ResourceState.LOADING -> {
                progress_bar.show()
            }
            ResourceState.SUCCESS -> {
                progress_bar.hide()
                resource.data?.let {
                    adapter.billsList = it.map { it -> mapper.mapToView(it) }
                    adapter.notifyDataSetChanged()
                }
            }
            ResourceState.ERROR -> {
                progress_bar.hide()
            }
        }
    }


    private fun constructRevealSettings(): RevealAnimationSettings {
        val fabX = (fab_add_bill.x + fab_add_bill.width / 2).toInt()
        val fabY = (fab_add_bill.y + fab_add_bill.height / 2).toInt()
        val containerW = bills_container.width
        val containerH = bills_container.height
        return RevealAnimationSettings(fabX, fabY, containerW, containerH)
    }

    private val listener = object : BillOnClickListener {
        override fun onBillClicked(billId: String) {
            navigationController.navigateToBillDetailFragment()
        }


    }
}
