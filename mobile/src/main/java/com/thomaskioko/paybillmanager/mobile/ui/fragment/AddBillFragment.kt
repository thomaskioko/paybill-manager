package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thomaskioko.paybillmanager.domain.model.Category
import com.thomaskioko.paybillmanager.mobile.R
import com.thomaskioko.paybillmanager.mobile.extension.hide
import com.thomaskioko.paybillmanager.mobile.extension.show
import com.thomaskioko.paybillmanager.mobile.injection.Injectable
import com.thomaskioko.paybillmanager.mobile.mapper.CategoryViewMapper
import com.thomaskioko.paybillmanager.mobile.ui.NavigationController
import com.thomaskioko.paybillmanager.mobile.ui.adapter.CategoriesAdapter
import com.thomaskioko.paybillmanager.mobile.ui.util.DismissableAnimation
import com.thomaskioko.paybillmanager.mobile.ui.util.RevealAnimationSettings
import com.thomaskioko.paybillmanager.mobile.ui.util.registerCircularRevealAnimation
import com.thomaskioko.paybillmanager.mobile.ui.util.startCircularExitAnimation
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.CreateCategoryViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.GetCategoriesViewModel
import kotlinx.android.synthetic.main.fragment_add_bill.*
import kotlinx.android.synthetic.main.layout_keypad.*
import timber.log.Timber
import javax.inject.Inject


class AddBillFragment : Fragment(), Injectable, DismissableAnimation {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mapper: CategoryViewMapper

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var getCategoriesViewModel: GetCategoriesViewModel

    @Inject
    lateinit var createCategoryViewModel: CreateCategoryViewModel

    @Inject
    lateinit var navigationController: NavigationController


    companion object {
        const val ARG_REVEAL = "args_reveal"
        fun newInstance(revealAnimationSettings: RevealAnimationSettings): AddBillFragment {
            val bundle = Bundle()
            bundle.putParcelable(ARG_REVEAL, revealAnimationSettings)
            val fragment = AddBillFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_add_bill, container, false)

        val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
        registerCircularRevealAnimation(view!!,
                revealAnim,
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                ContextCompat.getColor(context!!, R.color.white))

        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
                Timber.d("@onKey Back listener is working!!!")
                navigationController.navigateToBillsListFragment()
                return@OnKeyListener true
            }
            false
        })

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        createCategoryViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(CreateCategoryViewModel::class.java)

        getCategoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GetCategoriesViewModel::class.java)


        setUpRecyclerView()


        fab_add_bill.setOnClickListener {
          navigationController.navigateToBottomDialogFragment()
        }
    }

    override fun onStart() {
        super.onStart()
        getCategoriesViewModel.getCategories().observe(this,
                Observer<Resource<List<CategoryView>>> { it ->
                    it?.let { handleCategoriesData(it) }
                })

        getCategoriesViewModel.fetchCategories()
    }


    private fun setUpRecyclerView() {
        recycler_view_add_bills.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        recycler_view_add_bills.adapter = categoriesAdapter

    }


    private fun handleCategoriesData(listResource: Resource<List<CategoryView>>) {
        when (listResource.status) {
            ResourceState.SUCCESS -> {
                updateCategories(listResource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                recycler_view_add_bills.hide()
            }

            ResourceState.ERROR -> {
                Timber.e(listResource.message)
            }
        }
    }

    private fun updateCategories(list: List<Category>?) {
        recycler_view_add_bills.show()
        if (list!!.isEmpty()) {
            for (category in categoriesList()) {
                createCategoryViewModel.createCategory(category)
            }
        } else {
            categoriesAdapter.categoriesList = list
            categoriesAdapter.notifyDataSetChanged()
        }
    }


    override fun dismiss(listner: DismissableAnimation.OnDismissedListener) {
        val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
        startCircularExitAnimation(view!!, revealAnim,
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                ContextCompat.getColor(context!!, R.color.white),
                object : DismissableAnimation.OnDismissedListener {
                    override fun onDismissed() {
                        listner.onDismissed()
                    }
                })
    }


    private fun categoriesList(): List<Category> {
        val categories: MutableList<Category> = mutableListOf()

        categories.add(Category("1", "Baby", R.drawable.ic_baby_buggy_24dp))
        categories.add(Category("2", "Education", R.drawable.ic_education_24dp))
        categories.add(Category("3", "Fitness", R.drawable.ic_fitness_center_24dp))
        categories.add(Category("4", "Food & Drinks", R.drawable.ic_local_dining_24dp))
        categories.add(Category("5", "Fuel", R.drawable.ic_fuel_24dp))
        categories.add(Category("6", "Health", R.drawable.ic_health_24dp))
        categories.add(Category("7", "House", R.drawable.ic_house_24dp))
        categories.add(Category("8", "Internet", R.drawable.ic_wifi_24dp))
        categories.add(Category("9", "Pets", R.drawable.ic_pets_24dp))
        categories.add(Category("10", "Shopping", R.drawable.ic_shopping_cart_24dp))
        categories.add(Category("11", "Transportation", R.drawable.ic_transport_24dp))
        categories.add(Category("12", "Travelling", R.drawable.ic_travel_24dp))

        return categories
    }


}
