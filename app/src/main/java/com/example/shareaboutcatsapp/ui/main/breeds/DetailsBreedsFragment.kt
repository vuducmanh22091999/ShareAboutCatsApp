package com.example.shareaboutcatsapp.ui.main.breeds

import android.annotation.SuppressLint
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_details_breeds.*

class DetailsBreedsFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_breeds
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
        getData()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val bundle = arguments
        if (bundle != null) {
            val breedsModelItem = bundle.getSerializable("detailsBreeds") as BreedsModelItem
            breedsModelItem.let {
                tvIDBreeds.text = breedsModelItem.id
                tvNameBreeds.text = breedsModelItem.name + ","
                tvOriginBreeds.text = breedsModelItem.origin
                tvDescriptionBreeds.text = breedsModelItem.description
                tvLifeSpanBreeds.text = breedsModelItem.life_span
                tvAdaptabilityBreeds.text = breedsModelItem.adaptability.toString()
                tvAffectionLevelBreeds.text = breedsModelItem.affection_level.toString()
                tvTemperamentBreeds.text = breedsModelItem.temperament
                tvChildFriendlyBreeds.text = breedsModelItem.child_friendly.toString()
                tvIntelligenceBreeds.text = breedsModelItem.intelligence.toString()
                tvWikipediaLinkBreeds.text = breedsModelItem.wikipedia_url
            }
        } else {
            Toast.makeText(context, "Null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initListener() {
        imgBackHome.setOnClickListener(this)
    }

    private fun backToHome() {
        parentFragmentManager.popBackStack()
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBackHome -> backToHome()
        }
    }
}