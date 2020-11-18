package com.example.shareaboutcatsapp.ui.main.favourites

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.favourites.adapter.ListFavouritesAdapter
import com.example.shareaboutcatsapp.ui.main.favourites.details.DetailsFavouritesFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.dialog_delete.*
import kotlinx.android.synthetic.main.fragment_favourites.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.collections.ArrayList

class FavouritesFragment : BaseFragment(), View.OnClickListener {
    private val favouritesViewModel: FavouritesViewModel by viewModel()
    private lateinit var listFavouritesAdapter: ListFavouritesAdapter
    private var dataListFavourites: ArrayList<String> = ArrayList()
    lateinit var dialog: Dialog
    var favouritesModel = FavouritesModel()
    var indexDel = 0

    override fun getLayoutID(): Int {
        return R.layout.fragment_favourites
    }

    override fun doViewCreated() {
        showLoading()
        showBottomNavigation()
        initListener()
        setUpViewModel()
    }

    private fun setUpViewModel() {
        favouritesViewModel.getFavourites(getString(R.string.x_api_key))
        favouritesViewModel.favourites.observe(this, {
            setUpRecyclerView(it)
            hideLoading()
        })

        favouritesViewModel.favourites.observe(this, {
            dataListFavourites.clear()
            it.forEach { favouritesModelItem ->
                dataListFavourites.add(favouritesModelItem.sub_id)
            }
            val arrayAdapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_dropdown_item,
                dataListFavourites
            )
            autoSearchFavourites.setAdapter(arrayAdapter)
        })
    }

    private fun setUpRecyclerView(favouritesModel: FavouritesModel) {
        this.favouritesModel = favouritesModel
        listFavouritesAdapter = ListFavouritesAdapter(this.favouritesModel, {
            detailsFavourites(it)
        },
            { index, favouritesID ->
                indexDel = index
                openDialogDelete(favouritesID)
            })
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListMyFavourites.setHasFixedSize(true)
        rcvListMyFavourites.layoutManager = linearLayoutManager
        listFavouritesAdapter.notifyDataSetChanged()
        rcvListMyFavourites.adapter = listFavouritesAdapter
    }

    private fun detailsFavourites(index: Int) {
        val detailsFavouritesFragment = DetailsFavouritesFragment()
        val bundle = Bundle()
        val favouritesModelItem = favouritesViewModel.favourites.value?.get(index)
        bundle.putSerializable("detailsFavourites", favouritesModelItem)
        detailsFavouritesFragment.arguments = bundle
        addFragment(detailsFavouritesFragment, R.id.flContentScreens)
    }

    private fun deleteFavourites(favouritesID: Int) {
        favouritesViewModel.deleteFavourites(getString(R.string.x_api_key), favouritesID)
        this.favouritesModel.removeAt(indexDel)
        listFavouritesAdapter.notifyItemRemoved(indexDel)
        favouritesViewModel.favourites.observe(this, {
            setUpRecyclerView(it)
        })
    }

    private fun openDialogDelete(favouritesID: Int) {
        dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_delete)


        dialog.linearYes.setOnClickListener {
            deleteFavourites(favouritesID)
            dialog.dismiss()
            context?.let { Toasty.success(it, "Success", Toast.LENGTH_SHORT).show() }
        }

        dialog.linearNo.setOnClickListener {
            dialog.dismiss()
            Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }

//    private fun searchFavourites() {
//        val list = dataListFavourites.toMutableList()
//
//        autoSearchFavourites.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                if (s.toString().isNotEmpty()) {
//                    for (item in list) {
//                        if (autoSearchFavourites.text.toString().toLowerCase(Locale.ROOT)
//                                .contains(s.toString().toLowerCase(Locale.ROOT))) {
//                            dataListFavourites.add(item)
//                        }
//                    }
//                    listFavouritesAdapter.notifyDataSetChanged()
//                    rcvListMyFavourites.adapter = listFavouritesAdapter
//                }
//            }
//
//            override fun afterTextChanged(s: Editable?) {
//
//            }
//
//        })
//    }

    private fun initListener() {
        imgSearchFavourites.setOnClickListener(this)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgSearchFavourites -> {
//                dataListFavourites.clear()
//                searchFavourites()
            }
        }
    }
}