package com.example.shareaboutcatsapp.ui.main.home.full_image

import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
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
//        context?.let {
//            Glide.with(it).load(from).placeholder(R.drawable.img_placeholder).into(imgFullSize)
//        }

        arguments?.let {
            val args = FullImageFragmentArgs.fromBundle(it)
            context?.let { context ->
                Glide.with(context).load(args.from).placeholder(R.drawable.img_placeholder)
                    .into(imgFullSize)
            }
        }
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    private fun handleBottomNavigation() {
//        val bundle = arguments
//        val from = bundle?.getString("from1")
//        if (from == "home") {
//            if (activity is MainActivity) {
//                (activity as MainActivity).showBottomNavigation()
//            }
//        } else {
//            hideBottomNavigation()
//        }

        arguments?.let {
            val args = FullImageFragmentArgs.fromBundle(it)
            if (args.from1 == "home") {
                if (activity is MainActivity) {
                    (activity as MainActivity).showBottomNavigation()
                }
            }
            else {
                hideBottomNavigation()
            }
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackHomeFromFullImage -> {
//                parentFragmentManager.popBackStack()
                findNavController().popBackStack()
                handleBottomNavigation()
            }

        }
    }

}