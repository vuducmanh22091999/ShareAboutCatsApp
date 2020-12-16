package com.example.shareaboutcatsapp.ui.main.details_categories

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.details_categories.adapter.ListImageCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import com.example.shareaboutcatsapp.ui.main.home.full_image.FullImageFragment
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.fragment_details_categories.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsCategoriesFragment : BaseFragment(), View.OnClickListener {
    private lateinit var listImageCategoriesAdapter: ListImageCategoriesAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    private var imageModel = ImageModel()
    var list: ArrayList<ImageModelItem> = ArrayList()
    var categoryID: Int = 0
    var page = 1
    var limit = 10

    override fun getLayoutID(): Int {
        return R.layout.fragment_details_categories
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        getDataArguments()
        initRecyclerView()
        setInfo()
        setUpViewModel()
        checkWifi()
        initListener()
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
        } else {
            tvNotFound.visibility = View.VISIBLE
//            homeViewModel.getDataDetailsCategories()
        }
    }

    private fun initRecyclerView() {
        listImageCategoriesAdapter = ListImageCategoriesAdapter(this.imageModel) { index, id ->
            fullImage(id)
        }

//        val layoutManager = FlexboxLayoutManager(context)
//        layoutManager.flexDirection = FlexDirection.COLUMN
//        layoutManager.justifyContent = JustifyContent.FLEX_END
//        layoutManager.flexWrap=FlexWrap.WRAP
//        val gridLayoutManager = GridLayoutManager(context, 2)
//        rcvListCategoriesImage.setHasFixedSize(true)
//        rcvListCategoriesImage.layoutManager = gridLayoutManager
//        listImageCategoriesAdapter.StaggeredGridLayoutAdapter(this)
//        listImageCategoriesAdapter.notifyDataSetChanged()
//        rcvListCategoriesImage.adapter = listImageCategoriesAdapter

        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        rcvListCategoriesImage.layoutManager = staggeredGridLayoutManager
        listImageCategoriesAdapter.notifyDataSetChanged()
        rcvListCategoriesImage.adapter = listImageCategoriesAdapter
    }

    private fun initListener() {
        imgBackHomeFromDetailsCategories.setOnClickListener(this)
        nestedScrollView.setOnScrollChangeListener { nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight) {
                page++
                limit += limit
                progressBarDetailsCategories.visibility = View.VISIBLE
                callApi()
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
        homeViewModel.getImage(
            getString(R.string.x_api_key),
            categoryID,
            limit,
            page
        )
    }

    private fun getDataArguments() {
        val bundle = arguments
        if (bundle != null) {
            categoryID = bundle.getInt("categoryID")
        }
    }

    private fun setUpViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.image.observe(this@DetailsCategoriesFragment, {
                handleData(it)
            })
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setInfo() {
        val bundle = arguments
        if (bundle != null) {
            tvIDCategories.text = bundle.getInt("categoryID").toString() + "."
            tvNameCategories.text = bundle.getString("categoryName")
        }
    }

    private fun handleData(imageModel: ImageModel) {
//        if (page == 1) {
//            this.imageModel.addAll(imageModel)
//        } else {
//            this.imageModel.addAll(imageModel)
//        }
        this.imageModel.addAll(imageModel)

        listImageCategoriesAdapter.notifyDataSetChanged()
    }

    private fun fullImage(id: String) {
        val fullImageFragment = FullImageFragment()
        val bundle = Bundle()
//        val imageModelItem = homeViewModel.image.value?.find { it.id == id }
        val imageModelItem = imageModel.find { imageModelItem ->
            imageModelItem.id == id
        }
//        bundle.putSerializable("fullImage", imageModelItem)
        bundle.putString("from", imageModelItem?.url)
        bundle.putString("from1", "detailsCategories")
        fullImageFragment.arguments = bundle
        addFragment(
            fullImageFragment,
            R.id.flContentScreens,
            R.anim.slide_blink,
            R.anim.slide_blink,
            R.anim.slide_in_right,
            R.anim.slide_out_left
        )
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackHomeFromDetailsCategories -> backToHome()
        }
    }
}