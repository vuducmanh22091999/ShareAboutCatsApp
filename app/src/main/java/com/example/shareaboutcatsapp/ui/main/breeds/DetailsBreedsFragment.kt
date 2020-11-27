package com.example.shareaboutcatsapp.ui.main.breeds

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.details_categories.adapter.ListImageCategoriesAdapter
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import kotlinx.android.synthetic.main.fragment_details_breeds.*
import kotlinx.android.synthetic.main.fragment_details_breeds.nestedScrollView
import kotlinx.android.synthetic.main.fragment_details_categories.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsBreedsFragment : BaseFragment(), View.OnClickListener {
    private lateinit var listImageBreedsAdapter: ListImageBreedsAdapter
    private val homeViewModel: HomeViewModel by viewModel()
    private val breedsModel = BreedsModel()
    var page = 1
    var limit = 10
    var breedsID: String = ""

    override fun getLayoutID(): Int {
        return R.layout.fragment_details_breeds
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initRecyclerView()
        initListener()
        getData()
    }

    @SuppressLint("SetTextI18n")
    private fun getData() {
        val bundle = arguments
        if (bundle != null) {
            if (bundle.getSerializable("detailsBreeds") != null) {
                val breedsModelItem: BreedsModelItem? =
                    bundle.getSerializable("detailsBreeds") as BreedsModelItem
                breedsModelItem?.let {
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
                nestedScrollView.visibility = View.INVISIBLE
                tvError.visibility = View.VISIBLE
                imgNotFound.visibility = View.VISIBLE
            }
        }
    }

    private fun initRecyclerView() {
        listImageBreedsAdapter = ListImageBreedsAdapter(this.breedsModel)

        val linearLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rcvListImageBreeds.setHasFixedSize(true)
        rcvListImageBreeds.layoutManager = linearLayoutManager
        listImageBreedsAdapter.notifyDataSetChanged()
        rcvListImageBreeds.adapter = listImageBreedsAdapter
    }

    private fun callApi() {
//        homeViewModel.getImageBreedsByBreedsID(
//            getString(R.string.x_api_key),
//            breedsID, limit, page
//        )
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
        when (v.id) {
            R.id.imgBackHome -> backToHome()
        }
    }
}
