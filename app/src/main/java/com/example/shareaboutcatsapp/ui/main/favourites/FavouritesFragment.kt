package com.example.shareaboutcatsapp.ui.main.favourites

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.favourites.adapter.ListFavouritesAdapter
import com.example.shareaboutcatsapp.ui.main.favourites.details.DetailsFavouritesFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.dialog_delete.*
import kotlinx.android.synthetic.main.fragment_details_categories.*
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class FavouritesFragment : BaseFragment() {
    private val favouritesViewModel: FavouritesViewModel by viewModel()
    private lateinit var listFavouritesAdapter: ListFavouritesAdapter
    private var dataListFavourites: ArrayList<String> = ArrayList()
    lateinit var dialog: Dialog
    var favouritesModel = FavouritesModel()
    var indexDel = 0
    var limit = 10
    var page = 1
    var isLoading = true
    var currentItems = 0
    var totalItems = 0
    var scrollOutItems = 0

    override fun getLayoutID(): Int {
        return R.layout.fragment_favourites
    }

    override fun doViewCreated() {
        checkWifi()
        init()
        showBottomNavigation()
        setUpViewModel()
        searchFavourites()
    }

    // nhớ kiểm tra có wifi mới cho loadmore không thì gọi data local
    private fun loadMore() {
        nestedScrollViewFavourites.setOnScrollChangeListener { nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight && autoSearchFavourites.text.toString()
                    .isEmpty()
            ) {
                page++
                progressBarFavourites.visibility = View.VISIBLE
                callApi()
            }
        }

//        rcvListMyFavourites.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                if (dy > 0) {
//                    if (isLoading) {
//                        if ((currentItems + scrollOutItems) >= totalItems) {
//                            isLoading = false
//                            page++
//                            progressBarFavourites.visibility = View.VISIBLE
//                            callApi()
//                        }
//                    }
//                }
//            }
//        })
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
            loadMore()
        } else {
            favouritesViewModel.getDataFavourites()
        }
    }

    private fun callApi() {
        favouritesViewModel.getFavourites(getString(R.string.x_api_key), limit, page)
    }

    private fun setUpViewModel() {
        favouritesViewModel.favourites.observe(this, {
            favouritesViewModel.saveDataFavourites(it)
            handleData(it)
            progressBarFavourites.visibility = View.GONE
        })

//        favouritesViewModel.favourites.observe(this, {
//            dataListFavourites.clear()
//            it.forEach { favouritesModelItem ->
//                dataListFavourites.add(favouritesModelItem.sub_id)
//                favouritesViewModel.saveDataFavourites(it)
//            }
//            val arrayAdapter = ArrayAdapter<String>(
//                context!!,
//                android.R.layout.simple_spinner_dropdown_item,
//                dataListFavourites
//            )
//            autoSearchFavourites.setAdapter(arrayAdapter)
//        })
    }

    private fun handleData(favouritesModel: FavouritesModel) {
        this.favouritesModel.addAll(favouritesModel)
        listFavouritesAdapter.notifyDataSetChanged()
    }

    private fun init() {
        listFavouritesAdapter = ListFavouritesAdapter(this.favouritesModel, { index, favouritesID ->
            if (autoSearchFavourites.text.toString().isNotEmpty()) {
                hideKeyboard()
                detailsFavourites(favouritesID)
            } else {
                detailsFavourites(favouritesID)
            }
        },
            { index, favouritesID ->
                if ((activity as MainActivity).checkWifi() == true) {
                    indexDel = index
                    openDialogDelete(favouritesID)
                } else {
                    Toasty.error(context!!, "Wifi is not connected", Toasty.LENGTH_SHORT).show()
                }
            })
        val gridLayoutManager = GridLayoutManager(context, 2)
        currentItems = gridLayoutManager.childCount
        totalItems = gridLayoutManager.itemCount
        scrollOutItems = gridLayoutManager.findFirstVisibleItemPosition()
        rcvListMyFavourites.setHasFixedSize(true)
        rcvListMyFavourites.layoutManager = gridLayoutManager
        rcvListMyFavourites.adapter = listFavouritesAdapter
    }

    private fun setUpRecyclerView(favouritesModel: FavouritesModel) {
//        if (page == 1) {
//            this.favouritesModel = favouritesModel
//        } else {
//            this.favouritesModel.addAll(favouritesModel)
//        }
        this.favouritesModel.addAll(favouritesModel)
        listFavouritesAdapter = ListFavouritesAdapter(this.favouritesModel, { index, favouritesID ->
            if (autoSearchFavourites.text.toString().isNotEmpty()) {
                hideKeyboard()
                detailsFavourites(favouritesID)
            } else {
                detailsFavourites(favouritesID)
            }
        },
            { index, favouritesID ->
                if ((activity as MainActivity).checkWifi() == true) {
                    indexDel = index
                    openDialogDelete(favouritesID)
                } else {
                    Toasty.error(context!!, "Wifi is not connected", Toasty.LENGTH_SHORT).show()
                }
            })
        val gridLayoutManager = GridLayoutManager(context, 2)
        rcvListMyFavourites.setHasFixedSize(true)
        rcvListMyFavourites.layoutManager = gridLayoutManager
        listFavouritesAdapter.notifyDataSetChanged()
        rcvListMyFavourites.adapter = listFavouritesAdapter
    }

    private fun detailsFavourites(id: Int) {
        val detailsFavouritesFragment = DetailsFavouritesFragment()
        val bundle = Bundle()
//        val favouritesModelItem = favouritesViewModel.favourites.value?.find { it.id == id }
        val favouritesModelItem = favouritesModel.find { favouritesModelItem ->
            favouritesModelItem.id == id
        }
        bundle.putSerializable("detailsFavourites", favouritesModelItem)
        detailsFavouritesFragment.arguments = bundle
        addFragment(detailsFavouritesFragment, R.id.flContentScreens, R.anim.slide_in_right, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun deleteFavourites(favouritesID: Int) {
        favouritesViewModel.deleteFavourites(getString(R.string.x_api_key), favouritesID)
        this.favouritesModel.removeAt(indexDel)
        listFavouritesAdapter.notifyItemRemoved(indexDel)
        favouritesViewModel.favourites.observe(this, {
//            setUpRecyclerView(it)
            init()
        })
    }

    private fun openDialogDelete(favouritesID: Int) {
        dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_delete)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        dialog.tvDelete.setOnClickListener {
            deleteFavourites(favouritesID)
            dialog.dismiss()
            context?.let { Toasty.success(it, "Success", Toast.LENGTH_SHORT).show() }
        }

        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }


    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    private fun searchFavourites() {
        autoSearchFavourites.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    filterFavourites(s.toString())
                    clearText()
                } else {
                    imgClearTextSearchFavourites.visibility = View.INVISIBLE
                    imgNotFound.visibility = View.INVISIBLE
                    tvFavouritesNotFound.visibility = View.INVISIBLE
                    init()
                }
            }
        })
    }

    private fun filterFavourites(keyWord: String) {
        val searchFavourites: ArrayList<FavouritesModelItem> = ArrayList()
        for (favouritesModelItem in favouritesModel) {
            if (favouritesModelItem.sub_id.toLowerCase(Locale.ROOT).contains(
                    keyWord.toLowerCase(
                        Locale.ROOT
                    )
                )
            ) {
                searchFavourites.add(favouritesModelItem)
                imgNotFound.visibility = View.INVISIBLE
                tvFavouritesNotFound.visibility = View.INVISIBLE
            }
        }
        if (searchFavourites.size == 0) {
            imgNotFound.visibility = View.VISIBLE
            tvFavouritesNotFound.visibility = View.VISIBLE
        }
        listFavouritesAdapter.filterFavourites(searchFavourites)
    }

    private fun clearText() {
        if (autoSearchFavourites.text.toString().isNotEmpty()) {
            imgClearTextSearchFavourites.visibility = View.VISIBLE
            imgClearTextSearchFavourites.setOnClickListener {
                autoSearchFavourites.setText("")
                hideKeyboard()
//                init()
                imgNotFound.visibility = View.INVISIBLE
                tvFavouritesNotFound.visibility = View.INVISIBLE
                rcvListMyFavourites.adapter = listFavouritesAdapter
                imgClearTextSearchFavourites.visibility = View.INVISIBLE
            }
        }
    }
}