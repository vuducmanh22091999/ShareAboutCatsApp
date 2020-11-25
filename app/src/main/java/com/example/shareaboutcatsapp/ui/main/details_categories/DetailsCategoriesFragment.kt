package com.example.shareaboutcatsapp.ui.main.details_categories

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.details_categories.adapter.ListImageCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import com.example.shareaboutcatsapp.ui.main.home.full_image.FullImageFragment
import kotlinx.android.synthetic.main.fragment_details_categories.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsCategoriesFragment : BaseFragment(), View.OnClickListener {
    private lateinit var listImageCategoriesAdapter: ListImageCategoriesAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    var page = 1
    var limit = 10

    override fun getLayoutID(): Int {
        return R.layout.fragment_details_categories
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
        showLoading()
        checkWifi()
        setInfo()
        setUpViewModel()
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
        } else {
            tvNotFound.visibility = View.VISIBLE
//            homeViewModel.getDataDetailsCategories()
        }
    }

    private fun initListener() {
        imgBackHomeFromDetailsCategories.setOnClickListener(this)
        nestedScrollView.setOnScrollChangeListener { nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight) {
                page++
                progressBarDetailsCategories.visibility = View.VISIBLE
                callApi()
                setUpViewModel()
            }
        }
    }

    private fun backToHome() {
        parentFragmentManager.popBackStack()
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    private fun callApi() {
        val bundle = arguments
        if (bundle != null) {
            homeViewModel.getImage(
                getString(R.string.x_api_key),
                bundle.getInt("categoryID"),
                limit,
                page
            )

        }
    }

    private fun setUpViewModel() {
        homeViewModel.image.observe(this, {
//            homeViewModel.saveDetailsCategories(it)
            setUpRecyclerView(it)
        })
        hideLoading()
    }

    private fun setInfo() {
        val bundle = arguments
        if (bundle != null) {
            tvIDCategories.text = bundle.getInt("categoryID").toString() + "."
            tvNameCategories.text = bundle.getString("categoryName")
        }
    }

    private fun setUpRecyclerView(imageModel: ImageModel) {
        listImageCategoriesAdapter = ListImageCategoriesAdapter(imageModel) {
            fullImage(it)
        }
        val gridLayoutManager = GridLayoutManager(context, 2)
        rcvListCategoriesImage.setHasFixedSize(true)
        rcvListCategoriesImage.layoutManager = gridLayoutManager
        listImageCategoriesAdapter.notifyItemRangeChanged(10, 10 + 10)
        rcvListCategoriesImage.adapter = listImageCategoriesAdapter
    }

    private fun fullImage(index: Int) {
        val fullImageFragment = FullImageFragment()
        val bundle = Bundle()
        val imageModelItem = homeViewModel.image.value?.get(index)
        bundle.putSerializable("fullImage", imageModelItem)
        fullImageFragment.arguments = bundle
        addFragment(fullImageFragment, R.id.flContentScreens)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackHomeFromDetailsCategories -> backToHome()
        }
    }
}