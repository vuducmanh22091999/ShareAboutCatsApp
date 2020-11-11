package com.example.shareaboutcatsapp.ui.main.favourites.details

import android.view.View
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
        val bundle = arguments
        val favouritesModelItem =
            bundle?.getSerializable("detailsFavourites") as FavouritesModelItem
        val imageFavourites = favouritesModelItem.image.url
        val idFavourites = favouritesModelItem.id.toString()
        val imageID = favouritesModelItem.image_id
        val subID = favouritesModelItem.sub_id
        val userID = favouritesModelItem.user_id

        Glide.with(context!!).load(imageFavourites).into(imgFavourites)
        tvIDFavourites.text = idFavourites
        tvImageIDFavourites.text = imageID
        tvSubIDFavourites.text = subID
        tvUserIDFavourites.text = userID
    }

    private fun initListener() {
        imgBackMyFavourites.setOnClickListener(this)
    }

    private fun backToMyFavourites() {
        parentFragmentManager.popBackStack()
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