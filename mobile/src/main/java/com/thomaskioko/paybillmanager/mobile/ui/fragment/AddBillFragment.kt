package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.thomaskioko.paybillmanager.mobile.ui.view.CustomKeyboardView
import com.thomaskioko.paybillmanager.presentation.model.CategoryView
import com.thomaskioko.paybillmanager.presentation.state.Resource
import com.thomaskioko.paybillmanager.presentation.state.ResourceState
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.CreateCategoryViewModel
import com.thomaskioko.paybillmanager.presentation.viewmodel.category.GetCategoriesViewModel
import kotlinx.android.synthetic.main.fragment_add_bill.*
import timber.log.Timber
import javax.inject.Inject


class AddBillFragment : Fragment(), Injectable, DismissableAnimation,
        CategoriesAdapter.OnRecyclerViewItemClickListener, CustomKeyboardView.KeyboardListener {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var mapper: CategoryViewMapper
    @Inject
    lateinit var getCategoriesViewModel: GetCategoriesViewModel
    @Inject
    lateinit var createCategoryViewModel: CreateCategoryViewModel
    @Inject
    lateinit var navigationController: NavigationController

    private lateinit var categoriesAdapter: CategoriesAdapter


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
        view.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
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


        keyboardView.showKeyboard(et_amount)
        keyboardView.setKeyboardListener(this)
        setUpRecyclerView()

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (til_amount.isErrorEnabled)
                    til_amount.isErrorEnabled = false
            }
        })

        btn_delete.setOnClickListener {
            val stringBuilder = StringBuilder(et_amount.text.toString())
            if (stringBuilder.isNotEmpty() && stringBuilder.toString() != "0.0") {
                stringBuilder.delete(stringBuilder.length - 1, stringBuilder.length)
                et_amount.setText(stringBuilder.toString())
                keyboardView.showKeyboard(et_amount)
            }
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

    override fun onOKResult(amount: String) {
        if (amount.isEmpty()) {
            til_amount.isErrorEnabled = true
            til_amount.error = resources.getString(R.string.error_no_amount)
        } else {
            navigationController.navigateToBottomDialogFragment()
        }
    }


    private fun setUpRecyclerView() {
        categoriesAdapter = CategoriesAdapter(this)
        recycler_view_categories.layoutManager = LinearLayoutManager(
                activity, RecyclerView.HORIZONTAL, false
        )
        recycler_view_categories.adapter = categoriesAdapter
        categoriesAdapter.notifyDataSetChanged()

    }

    override fun selectedCategoryItem(category: Category) {
    }


    private fun handleCategoriesData(listResource: Resource<List<CategoryView>>) {
        when (listResource.status) {
            ResourceState.SUCCESS -> {
                updateCategories(listResource.data?.map {
                    mapper.mapToView(it)
                })
            }
            ResourceState.LOADING -> {
                recycler_view_categories.hide()
            }

            ResourceState.ERROR -> {
                Timber.e(listResource.message)
            }
        }
    }

    private fun updateCategories(list: List<Category>?) {
        recycler_view_categories.show()
        if (list!!.isEmpty()) {
            for (category in categoriesList()) {
                createCategoryViewModel.createCategory(category)
            }
        } else {
            categoriesAdapter.categoriesList = list
            categoriesAdapter.notifyDataSetChanged()
        }
    }


    override fun dismiss(listener: DismissableAnimation.OnDismissedListener) {
        val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
        startCircularExitAnimation(view!!, revealAnim,
                ContextCompat.getColor(context!!, R.color.colorPrimaryDark),
                ContextCompat.getColor(context!!, R.color.white),
                object : DismissableAnimation.OnDismissedListener {
                    override fun onDismissed() {
                        listener.onDismissed()
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

