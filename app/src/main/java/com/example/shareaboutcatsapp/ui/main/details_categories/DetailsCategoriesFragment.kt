package com.example.shareaboutcatsapp.ui.main.details_categories

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.details_categories.adapter.ListImageCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_details_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsCategoriesFragment : BaseFragment(), View.OnClickListener {
    private lateinit var listImageCategoriesAdapter: ListImageCategoriesAdapter
    private val homeViewModel: HomeViewModel by viewModel()

    override fun getLayoutID(): Int {
        return R.layout.fragment_details_categories
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        showLoading()
        checkWifi()
        setInfo()
        setUpViewModel()
        initListener()
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
        } else {
            Toast.makeText(context, "abc", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {
        imgBackHomeFromDetailsCategories.setOnClickListener(this)
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
                10,
                1
            )
        }
    }

    private fun setUpViewModel() {
        homeViewModel.image.observe(this, {
            setUpRecyclerView(it)
//            hideLoading()
        })
        hideLoading()
    }

    private fun setInfo() {
        val bundle = arguments
        if (bundle != null) {
            tvIDCategories.text = bundle.getInt("categoryID").toString()
            tvNameCategories.text = bundle.getString("categoryName")
        }
    }

    private fun setUpRecyclerView(imageModel: ImageModel) {
        listImageCategoriesAdapter = ListImageCategoriesAdapter(imageModel)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListCategoriesImage.setHasFixedSize(true)
        rcvListCategoriesImage.layoutManager = linearLayoutManager
        rcvListCategoriesImage.adapter = listImageCategoriesAdapter
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackHomeFromDetailsCategories -> backToHome()
        }
    }
}