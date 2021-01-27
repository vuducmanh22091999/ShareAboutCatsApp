package com.example.shareaboutcatsapp.ui.main.favourites.details

import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_details_favourites.*

class DetailsFavouritesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_favourites
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
        getDataDetailsFavourites()
    }

    private fun getDataDetailsFavourites() {
//        val bundle = arguments
//        bundle?.getSerializable("detailsFavourites")?.let {
//            val favouritesModelItem = it as FavouritesModelItem
//
//            val imageFavourites = favouritesModelItem.image.url
//            val idFavourites = favouritesModelItem.id.toString()
//            val imageID = favouritesModelItem.image_id
//            val subID = favouritesModelItem.sub_id
//            val userID = favouritesModelItem.user_id
//
//            Glide.with(requireContext()).load(imageFavourites).placeholder(R.drawable.img_placeholder)
//                .into(imgFavourites)
//            tvIDFavourites.text = idFavourites
//            tvImageIDFavourites.text = imageID
//            tvSubIDFavourites.text = subID
//            tvUserIDFavourites.text = userID
//        }

        arguments?.let {
            val args = DetailsFavouritesFragmentArgs.fromBundle(it)
            Glide.with(requireContext()).load(args.detailsFavourites.image.url).placeholder(R.drawable.img_placeholder)
                .into(imgFavourites)
            tvIDFavourites.text = args.detailsFavourites.id.toString()
            tvImageIDFavourites.text = args.detailsFavourites.image_id
            tvSubIDFavourites.text = args.detailsFavourites.sub_id
            tvUserIDFavourites.text = args.detailsFavourites.user_id
        }
    }

    private fun initListener() {
        imgBackMyFavourites.setOnClickListener(this)
    }

    private fun backToMyFavourites() {
//        parentFragmentManager.popBackStack()
        findNavController().popBackStack()
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackMyFavourites -> backToMyFavourites()
        }
    }
}