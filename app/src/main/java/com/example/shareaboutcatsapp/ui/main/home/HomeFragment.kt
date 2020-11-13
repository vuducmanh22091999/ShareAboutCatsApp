package com.example.shareaboutcatsapp.ui.main.home

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.chat.ListChatFragment
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.adapter.ListFavouritesAdapter
import com.example.shareaboutcatsapp.ui.main.home.full_image.FullImageFragment
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.Serializable

class HomeFragment : BaseFragment(), View.OnClickListener {
    private var index: Int? = null
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

    private fun detailsBreedsIndex(index: Int) {
        homeViewModel.getBreeds(getString(R.string.x_api_key))
        homeViewModel.breeds.observe(this, {
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

        val detailsBreedsFragment = DetailsBreedsFragment()
        val bundle = Bundle()
        val name = autoSearchBreeds.text.toString()
        var breedsModelItem : BreedsModelItem? = null
        homeViewModel.breeds.value?.let {
            it.forEach {item ->
                if(item.name == name) {
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
        var breedsModelItem : BreedsModelItem? = null
        homeViewModel.breeds.value?.let {
            it.forEach {item ->
                if(item.name == name) {
                    breedsModelItem = item
                }
            }
        }
        bundle.putSerializable("detailsBreeds", breedsModelItem)
        detailsBreedsFragment.arguments = bundle
        replaceFragment(detailsBreedsFragment, R.id.flContentScreens)
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
    }

    private fun setUpRecyclerViewListFavourites(favouritesModel: FavouritesModel) {
        listFavouritesAdapter = ListFavouritesAdapter(favouritesModel) {
            fullSizeImage(it)
        }
        val gridLayoutManager = GridLayoutManager(context, 2)
        rcvListFavourites.setHasFixedSize(true)
        rcvListFavourites.layoutManager = gridLayoutManager
        rcvListFavourites.adapter = listFavouritesAdapter
    }

    private fun fullSizeImage(index: Int) {
        val fullImageFragment = FullImageFragment()
        val bundle = Bundle()
        val favouritesModelItem = homeViewModel.favourites.value?.get(index)
        bundle.putSerializable("fullSizeImage", favouritesModelItem)
        fullImageFragment.arguments = bundle
        replaceFragment(fullImageFragment, R.id.flContentScreens)
    }

    private fun setName() {
        tvUserName.text = appPreferences.getLoginUserName()
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
            R.id.imgChat -> openListChat()
//            R.id.imgSearchBreeds -> index?.let { openDetailsBreedsIndex(it) }
            R.id.imgSearchBreeds -> openDetailsBreeds()
        }
    }
}