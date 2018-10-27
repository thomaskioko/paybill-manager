package com.thomaskioko.paybillmanager.mobile.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
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
    lateinit var navigationController: NavigationController

    private lateinit var categoriesAdapter: CategoriesAdapter
    private var categoryId: String = ""


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

        if (activity!!.intent.hasExtra(ARG_REVEAL)) {
            val revealAnim: RevealAnimationSettings = arguments?.getParcelable(ARG_REVEAL)!!
            registerCircularRevealAnimation(view!!,
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

        getCategoriesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(GetCategoriesViewModel::class.java)

        btn_delete.isEnabled = false

        keyboardView.showKeyboard(et_amount)
        keyboardView.setKeyboardListener(this)
        setUpRecyclerView()

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (til_amount.isErrorEnabled)
                    til_amount.isErrorEnabled = false

                if (s.isNullOrEmpty()) {
                    btn_delete.isEnabled = false
                    btn_delete.setImageDrawable(
                            ResourcesCompat.getDrawable(resources, R.drawable.ic_backspace_disabled, null)
                    )
                } else {
                    btn_delete.isEnabled = true
                    btn_delete.setImageDrawable(
                            ResourcesCompat.getDrawable(resources, R.drawable.ic_backspace_enabled, null)
                    )
                }

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


    private fun setUpRecyclerView() {
        categoriesAdapter = CategoriesAdapter(this)
        recycler_view_categories.layoutManager = LinearLayoutManager(
                activity, RecyclerView.HORIZONTAL, false
        )
        recycler_view_categories.adapter = categoriesAdapter
        categoriesAdapter.notifyDataSetChanged()

    }

    override fun selectedCategoryItem(category: Category) {
        if (tv_error.visibility == View.VISIBLE) {
            tv_error.hide()
        }
        categoryId = category.id
    }

    override fun onOKResult(amount: String) {
        when {
            amount.isEmpty() -> {
                til_amount.isErrorEnabled = true
                til_amount.error = resources.getString(R.string.error_no_amount)
            }
            categoryId.isEmpty() -> {
                tv_error.show()
            }
            else -> navigationController.navigateToBillDetailsBottomDialogFragment(amount, categoryId)
        }
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
        if (!list!!.isEmpty()) {
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


}

