package com.example.shareaboutcatsapp.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.chat.ListChatFragment
import com.example.shareaboutcatsapp.ui.main.details_categories.DetailsCategoriesFragment
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListFavouritesAdapter
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListTest
import com.example.shareaboutcatsapp.ui.main.home.full_image.FullImageFragment
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.tvUserName
import kotlinx.android.synthetic.main.fragment_votes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : BaseFragment(), View.OnClickListener {
    private lateinit var appPreferences: AppPreferences
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var listCategoriesAdapter: ListCategoriesAdapter
    private lateinit var listFavouritesAdapter: ListFavouritesAdapter
    private lateinit var listTest: ListTest
    private var dataListBreeds: ArrayList<String> = ArrayList()
    var favouritesModel = FavouritesModel()
    var limit = 10
    var page = 1
    var isLoading = true
    var currentItems = 0
    var totalItems = 0
    var scrollOutItems = 0

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun doViewCreated() {
        appPreferences = context?.let { AppPreferences(it) }!!

        checkWifi()
        showLoading()
        showBottomNavigation()
        initListener()
        setInfo()
        setUpViewModel()
    }

    private fun initListener() {
        imgSearchBreeds.setOnClickListener(this)
    }

    private fun loadMore() {
        nestedScrollViewHome.setOnScrollChangeListener { nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight) {
                page++
                progressHome.visibility = View.VISIBLE
                callApi()
            }
        }

//        rcvListFavourites.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    if (isLoading) {
//                        if ((currentItems + scrollOutItems) >= totalItems) {
//                            isLoading = false
//                            page++
//                            progressHome.visibility = View.VISIBLE
//                            callApi()
//                        }
//                    }
//                }
//            }
//        })
    }


    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
            loadMore()
        } else {
            homeViewModel.getDataCategories()
            homeViewModel.getDataFavourites()
            homeViewModel.getDataBreeds()
//            homeViewModel.getImageBreeds()
        }
    }

    private fun callApi() {
        homeViewModel.getCategories(getString(R.string.x_api_key))
        homeViewModel.getFavourites(getString(R.string.x_api_key), limit, page)
        homeViewModel.getBreeds(getString(R.string.x_api_key))
    }


    private fun setUpViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.categories.observe(this@HomeFragment, {
                homeViewModel.saveDataCategories(it)
                setUpRecyclerViewListCategories(it)
            })
        }

        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.favourites.observe(this@HomeFragment, {
                homeViewModel.saveDataFavourites(it)
                setUpRecyclerViewListFavourites(it)
                hideLoading()
            })
        }

//        CoroutineScope(Dispatchers.Main).launch {
//            homeViewModel.imageBreeds.observe(this@HomeFragment, {
//                homeViewModel.saveImageBreeds(it)
//            })
//        }

        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.breeds.observe(this@HomeFragment, {
                dataListBreeds.clear()
                it.forEach { breedsModelItem ->
                    dataListBreeds.add(breedsModelItem.name)
                    homeViewModel.saveDataBreeds(it)
                }
                val arrayAdapter = ArrayAdapter<String>(
                    context!!,
                    android.R.layout.simple_spinner_dropdown_item,
                    dataListBreeds
                )
                autoSearchBreeds.setAdapter(arrayAdapter)
            })
        }
    }

    private fun detailsBreedsIndex(index: Int) {
        homeViewModel.getBreeds(getString(R.string.x_api_key))
        CoroutineScope(Dispatchers.Main).launch {
            homeViewModel.breeds.observe(this@HomeFragment, {
                dataListBreeds.clear()
                it.forEach { breedsModelItem ->
                    dataListBreeds.add(breedsModelItem.name)
                }
//            autoSearchBreeds.setOnItemClickListener { parent, view, position, id ->
//                index = position
//            }
                val arrayAdapter = ArrayAdapter<String>(
                    context!!,
                    android.R.layout.simple_spinner_dropdown_item,
                    dataListBreeds
                )
                autoSearchBreeds.setAdapter(arrayAdapter)
            })
        }

        val detailsBreedsFragment = DetailsBreedsFragment()
        val bundle = Bundle()
        val name = autoSearchBreeds.text.toString()
        var breedsModelItem: BreedsModelItem? = null
        homeViewModel.breeds.value?.let {
            it.forEach { item ->
                if (item.name == name) {
                    breedsModelItem = item
                }
            }
        }
        bundle.putSerializable("detailsBreeds", breedsModelItem)
        detailsBreedsFragment.arguments = bundle
        replaceFragment(detailsBreedsFragment, R.id.flContentScreens)
    }

    private fun detailsBreeds() {
        val detailsBreedsFragment = DetailsBreedsFragment()
        val bundle = Bundle()
        val name = autoSearchBreeds.text.toString()
        if (name == "" || name.trim().isEmpty()) {
            context?.let { Toasty.error(it, "Don't leave blank!!!", Toast.LENGTH_SHORT).show() }
        } else if (name.isNotEmpty()) {
            var breedsModelItem: BreedsModelItem? = null
            homeViewModel.breeds.value?.let {
                it.forEach { item ->
                    if (item.name == name) {
                        breedsModelItem = item
                    }
                }
            }
            hideKeyboard()
            bundle.putSerializable("detailsBreeds", breedsModelItem)
            detailsBreedsFragment.arguments = bundle
            addFragment(detailsBreedsFragment, R.id.flContentScreens)
        }
    }

    private fun initRecyclerView(imageModel: ImageModel) {
        listTest = ListTest(imageModel) { indexInfo ->
            context?.let {
                val bundle = Bundle()
                val detailCategoriesFragment = DetailsCategoriesFragment()
                bundle.putInt("categoryID", imageModel[indexInfo].categoriesModelItem.forEach {
                    it.id
                }.toString().toInt())
                bundle.putString("categoryName", imageModel[indexInfo].categoriesModelItem.forEach {
                    it.name
                }.toString())
                detailCategoriesFragment.arguments = bundle
                addFragment(detailCategoriesFragment, R.id.flContentScreens)
            }
        }
    }

    private fun setUpRecyclerViewListCategories(categoriesModel: CategoriesModel) {
        listCategoriesAdapter = ListCategoriesAdapter(categoriesModel) { indexInfo ->
            context?.let {
                val bundle = Bundle()
                val detailCategoriesFragment = DetailsCategoriesFragment()
                bundle.putInt("categoryID", categoriesModel[indexInfo].id)
                bundle.putString("categoryName", categoriesModel[indexInfo].name)
                detailCategoriesFragment.arguments = bundle
                addFragment(detailCategoriesFragment, R.id.flContentScreens)
            }
        }
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvListCategories.setHasFixedSize(true)
        rcvListCategories.layoutManager = linearLayoutManager
        rcvListCategories.adapter = listCategoriesAdapter
    }

    private fun setUpRecyclerViewListFavourites(favouritesModel: FavouritesModel) {
        this.favouritesModel.addAll(favouritesModel)
        listFavouritesAdapter = ListFavouritesAdapter(this.favouritesModel) { index, idFavourites ->
            fullSizeImage(idFavourites)
        }
        val gridLayoutManager = GridLayoutManager(context, 2)
        currentItems = gridLayoutManager.childCount
        totalItems = gridLayoutManager.itemCount
        scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition()
        rcvListFavourites.setHasFixedSize(true)
        rcvListFavourites.layoutManager = gridLayoutManager
        listFavouritesAdapter.notifyDataSetChanged()
        rcvListFavourites.adapter = listFavouritesAdapter

    }

    private fun fullSizeImage(idFavourites: Int) {
        val fullImageFragment = FullImageFragment()
        val bundle = Bundle()
        val favouritesModelItem = favouritesModel.find { favouritesModelItem ->
            favouritesModelItem.id == idFavourites
        }
//        bundle.putSerializable("fullSizeImage", favouritesModelItem)
        bundle.putString("from", favouritesModelItem?.image?.url)
        bundle.putString("from1", "home")
        fullImageFragment.arguments = bundle
        addFragment(fullImageFragment, R.id.flContentScreens)
    }

    private fun setInfo() {
        tvUserName.text = appPreferences.getLoginUserName()
        if (getIDUserFacebook().isNotEmpty()) {
            context?.let {
                Glide.with(it).load(urlAvatar()).placeholder(R.drawable.ic_account).into(imgChat)
            }
        } else {
            context?.let {
                Glide.with(it).load(appPreferences.getLoginAvatar())
                    .placeholder(R.drawable.ic_account).into(imgChat)
            }
        }
    }

    private fun getIDUserFacebook(): String {
        var facebookUserId = ""
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            for (profile in user.providerData) {
                if (FacebookAuthProvider.PROVIDER_ID == profile.providerId) {
                    facebookUserId = profile.uid
                }
            }
        }
        return facebookUserId
    }

    private fun urlAvatar(): String {
        return "https://graph.facebook.com/${getIDUserFacebook()}/picture?type=large"
    }

    private fun openListChat() {
        replaceFragment(ListChatFragment(), R.id.flContentScreens)
    }

    private fun openDetailsBreedsIndex(index: Int) {
        detailsBreedsIndex(index)
    }

    private fun openDetailsBreeds() {
        detailsBreeds()
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
//            R.id.imgSearchBreeds -> index?.let { openDetailsBreedsIndex(it) }
            R.id.imgSearchBreeds -> openDetailsBreeds()
        }
    }
}