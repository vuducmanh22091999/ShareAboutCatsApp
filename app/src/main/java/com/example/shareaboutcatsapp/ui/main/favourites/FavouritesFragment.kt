package com.example.shareaboutcatsapp.ui.main.favourites

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.favourites.details.DetailsFavouritesFragment
import kotlinx.android.synthetic.main.fragment_favourites.*

class FavouritesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_favourites
    }

    override fun doViewCreated() {
        showBottomNavigation()
        initListener()
    }

    private fun initListener() {
        tvTest.setOnClickListener(this)
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
        when(v.id) {
            R.id.tvTest -> detailsFavourites()
        }
    }
}