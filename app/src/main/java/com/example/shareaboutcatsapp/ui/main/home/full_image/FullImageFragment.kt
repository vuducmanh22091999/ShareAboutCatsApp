package com.example.shareaboutcatsapp.ui.main.home.full_image

import android.view.View
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_full_image.*

class FullImageFragment: BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_full_image
    }

    override fun doViewCreated() {
        initListener()
        hideBottomNavigation()
        showImage()
    }

    private fun initListener() {
        imgBackHomeFromFullImage.setOnClickListener(this)
    }

    private fun showImage() {
        val bundle = arguments
        val favouritesModelItem = bundle?.getSerializable("fullSizeImage") as FavouritesModelItem
        val imageFavourites = favouritesModelItem.image.url
        Glide.with(context!!).load(imageFavourites).into(imgFullSize)
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
       when(v.id) {
           R.id.imgBackHomeFromFullImage -> parentFragmentManager.popBackStack()

       }
    }

}