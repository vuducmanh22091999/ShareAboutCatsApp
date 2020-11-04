package com.example.shareaboutcatsapp.ui.main.favourites.details

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_details_favourites.*

class DetailsFavouritesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_favourites
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        imgBackMyFavourites.setOnClickListener(this)
    }

    private fun backToMyFavourites() {
        activity?.onBackPressed()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBackMyFavourites -> backToMyFavourites()
        }
    }
}