package com.example.shareaboutcatsapp.ui.main.home

import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.AppPreferences
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.chat.ListChatFragment
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListFavouritesAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), View.OnClickListener {
    private lateinit var appPreferences: AppPreferences
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var listCategoriesAdapter: ListCategoriesAdapter
    private lateinit var listFavouritesAdapter: ListFavouritesAdapter
    private var dataListBreeds: ArrayList<String> = ArrayList()

    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun doViewCreated() {
        appPreferences = context?.let { AppPreferences(it) }!!

//        showLoading()
        showBottomNavigation()
        initListener()
        setName()
        setUpViewModel()
    }

    private fun initListener() {
        imgChat.setOnClickListener(this)
        imgSearchBreeds.setOnClickListener(this)
    }

    private fun setUpViewModel() {
        homeViewModel.getCategories(getString(R.string.x_api_key))
        homeViewModel.categories.observe(this, {
            setUpRecyclerViewListCategories(it)
        })

        homeViewModel.getFavourites(getString(R.string.x_api_key))
        homeViewModel.favourites.observe(this, {
            setUpRecyclerViewListFavourites(it)
//            hideLoading()
        })


        homeViewModel.getBreeds(getString(R.string.x_api_key))
        homeViewModel.breeds.observe(this, {
            dataListBreeds.clear()
            it.forEach { breedsModelItem ->
                dataListBreeds.add(breedsModelItem.name)
            }
            val arrayAdapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_dropdown_item,
                dataListBreeds
            )
            autoSearchBreeds.setAdapter(arrayAdapter)
        })
    }

    private fun getData() {
        Toast.makeText(context, autoSearchBreeds.text, Toast.LENGTH_SHORT).show()
    }

    private fun setUpRecyclerViewListCategories(categoriesModel: CategoriesModel) {
        listCategoriesAdapter = ListCategoriesAdapter(categoriesModel) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvListCategories.setHasFixedSize(true)
        rcvListCategories.layoutManager = linearLayoutManager
        rcvListCategories.adapter = listCategoriesAdapter
        rcvListCategories.postDelayed({rcvListCategories.hideShimmerAdapter()}, 30000)
    }

    private fun setUpRecyclerViewListFavourites(favouritesModel: FavouritesModel) {
        listFavouritesAdapter = ListFavouritesAdapter(favouritesModel) {
            Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
        }
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListFavourites.setHasFixedSize(true)
        rcvListFavourites.layoutManager = linearLayoutManager
        rcvListFavourites.adapter = listFavouritesAdapter
        rcvListFavourites.postDelayed({rcvListCategories.hideShimmerAdapter()}, 50000)
    }

    private fun setName() {
        tvUserName.text = appPreferences.getLoginUserName()
    }

    private fun openListChat() {
        replaceFragment(ListChatFragment(), R.id.flContentScreens)
    }

    private fun openDetailsBreeds() {
        getData()
//        replaceFragment(DetailsBreedsFragment(), R.id.flContentScreens)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgChat -> openListChat()
            R.id.imgSearchBreeds -> openDetailsBreeds()
        }
    }
}