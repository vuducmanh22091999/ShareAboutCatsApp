package com.example.shareaboutcatsapp.ui.main.favourites

import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.favourites.adapter.ListFavouritesAdapter
import com.example.shareaboutcatsapp.ui.main.favourites.details.DetailsFavouritesFragment
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouritesFragment : BaseFragment(), View.OnClickListener {
    private val favouritesViewModel: FavouritesViewModel by viewModel()
    private lateinit var listFavouritesAdapter: ListFavouritesAdapter
    private var dataListFavourites: ArrayList<String> = ArrayList()

    override fun getLayoutID(): Int {
        return R.layout.fragment_favourites
    }

    override fun doViewCreated() {
        showBottomNavigation()
        initListener()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        favouritesViewModel.getFavourites(getString(R.string.x_api_key))
        favouritesViewModel.favourites.observe(this, {
            setUpRecyclerView(it)
        })

        favouritesViewModel.favourites.observe(this, {
            dataListFavourites.clear()
            it.forEach { favouritesModelItem ->
                dataListFavourites.add(favouritesModelItem.created_at)
            }
            val arrayAdapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_dropdown_item,
                dataListFavourites
            )
            autoSearchFavourites.setAdapter(arrayAdapter)
        })
    }

    private fun setUpRecyclerView(favouritesModel: FavouritesModel) {
        listFavouritesAdapter = ListFavouritesAdapter(favouritesModel)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListMyFavourites.setHasFixedSize(true)
        rcvListMyFavourites.layoutManager = linearLayoutManager
        rcvListMyFavourites.adapter = listFavouritesAdapter
        rcvListMyFavourites.postDelayed({ rcvListMyFavourites.showShimmerAdapter() }, 30000)
    }

    private fun initListener() {
    }

    private fun detailsFavourites() {
        replaceFragment(DetailsFavouritesFragment(), R.id.flContentScreens)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
        }
    }
}