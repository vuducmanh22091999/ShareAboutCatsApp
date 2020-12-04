package com.example.shareaboutcatsapp.ui.main.home.full_image

import android.view.View
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.home.HomeFragment
import kotlinx.android.synthetic.main.fragment_full_image.*

class FullImageFragment : BaseFragment(), View.OnClickListener {
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
        val from = bundle?.getString("from")
//        if (from == "home") {
//            val favouritesModelItem = bundle?.getSerializable("fullSizeImage") as FavouritesModelItem
//            val imageFavourites = favouritesModelItem.image.url
//            context?.let { Glide.with(it).load(imageFavourites).into(imgFullSize) }
//        } else {
//            val imageModelItem = bundle?.getSerializable("fullImage") as ImageModelItem
//            val imageCategories = imageModelItem.url
//            context?.let { Glide.with(it).load(imageCategories).into(imgFullSize) }
//        }
        context?.let {
            Glide.with(it).load(from).placeholder(R.drawable.img_placeholder).into(imgFullSize)
        }
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    private fun handleBottomNavigation() {
        val bundle = arguments
        val from = bundle?.getString("from1")
        if (from == "home") {
            if (activity is MainActivity) {
                (activity as MainActivity).showBottomNavigation()
            }
        } else {
            hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackHomeFromFullImage -> {
                parentFragmentManager.popBackStack()
                handleBottomNavigation()
            }

        }
    }

}