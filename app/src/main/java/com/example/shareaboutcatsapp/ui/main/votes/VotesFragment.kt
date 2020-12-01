package com.example.shareaboutcatsapp.ui.main.votes

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
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.votes.adapter.ListVotesAdapter
import com.example.shareaboutcatsapp.ui.main.votes.create_votes.CreateVotesFragment
import com.example.shareaboutcatsapp.ui.main.votes.details.DetailsVotesFragment
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.dialog_delete.*
import kotlinx.android.synthetic.main.fragment_votes.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class VotesFragment : BaseFragment() {
    private val votesViewModel: VotesViewModel by viewModel()
    private lateinit var listVotesAdapter: ListVotesAdapter
    private var dataListVotes: ArrayList<String> = ArrayList()
    lateinit var dialog: Dialog
    var votesModel = VotesModel()
    var limit = 20
    var page = 1

    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
        checkWifi()
        showBottomNavigation()
        setUpViewModel()
        searchVotes()
    }

    private fun loadMore() {
        nestedScrollViewVotes.setOnScrollChangeListener { nestedScrollView: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (scrollY == nestedScrollView.getChildAt(0).measuredHeight - nestedScrollView.measuredHeight && autoSearchVotes.text.toString().isEmpty()) {
                page++
                progressBarVotes.visibility = View.VISIBLE
                callApi()
            }
        }
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
            loadMore()
        } else {
            votesViewModel.getDataVotes()
        }
    }

    private fun callApi() {
        votesViewModel.getVotes(getString(R.string.x_api_key), limit, page)
    }

    private fun setUpViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            votesViewModel.votes.observe(this@VotesFragment, {
                votesViewModel.saveDataVotes(it)
                setUpRecyclerViewListVotes(it)
                progressBarVotes.visibility = View.GONE
            })
        }

//        votesViewModel.votes.observe(this@VotesFragment, {
//            dataListVotes.clear()
//            it.forEach { votesModelItem ->
//                dataListVotes.add((votesModelItem.id.toString()))
//                votesViewModel.saveDataVotes(it)
//            }
//            val arrayAdapter = ArrayAdapter<String>(
//                context!!,
//                android.R.layout.simple_spinner_dropdown_item,
//                dataListVotes
//            )
//            autoSearchVotes.setAdapter(arrayAdapter)
//        })
    }

    private fun detailsVotes(id: Int) {
        val detailsVotesFragment = DetailsVotesFragment()
        val bundle = Bundle()
//        val votesModelItem = votesViewModel.votes.value?.find { it.id == id }
        val votesModelItem = votesModel.find { it.id == id }

//        bundle.putSerializable("detailsVotes", votesModelItem)
        votesModelItem?.let {
            bundle.putSerializable("detailsVotes", votesModelItem)
            detailsVotesFragment.arguments = bundle
            addFragment(detailsVotesFragment, R.id.flContentScreens)
        }

    }

    private fun setUpRecyclerViewListVotes(votesModel: VotesModel) {
//        if (page == 1) {
//            this.votesModel = votesModel
//        } else {
            this.votesModel.addAll(votesModel)
//        }

        listVotesAdapter = ListVotesAdapter(this.votesModel, { index, idVotes ->
            if (autoSearchVotes.text.toString().isNotEmpty()) {
                hideKeyboard()
                detailsVotes(idVotes)
            } else {
                detailsVotes(idVotes)
            }

        },
            { index: Int, s: Int -> openDialogDelete(s) })
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListMyVotes.setHasFixedSize(true)
        rcvListMyVotes.layoutManager = linearLayoutManager
        listVotesAdapter.notifyDataSetChanged()
        rcvListMyVotes.adapter = listVotesAdapter
    }

    private fun openDialogDelete(voteID: Int) {
        dialog = Dialog(context!!)
        dialog.setContentView(R.layout.dialog_delete)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        dialog.tvDelete.setOnClickListener {
            if ((activity as MainActivity).checkWifi() == true) {
                deleteVotes(voteID)
                dialog.dismiss()
                showSnackbar()
            } else {
                Toasty.error(context!!, "Wifi is not connected", Toasty.LENGTH_SHORT).show()
                dialog.dismiss()
            }
        }

        dialog.tvCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun showSnackbar() {
        val snackbar =
            activity?.let {
                Snackbar.make(
                    it.findViewById(android.R.id.content),
                    "Failed",
                    Snackbar.LENGTH_SHORT
                )
            }
        snackbar?.setAction("Details", View.OnClickListener {
            addFragment(DetailsSnackBarFragment(), R.id.flContentScreens)
        })
        snackbar?.show()
    }

    private fun deleteVotes(votesID: Int) {
        votesViewModel.deleteVotes(getString(R.string.x_api_key), votesID)
        votesViewModel.votes.observe(this, {
            setUpRecyclerViewListVotes(it)
        })
    }

    private fun openCreateVotesScreen() {
        replaceFragment(CreateVotesFragment(), R.id.flContentScreens)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    private fun searchVotes() {
        autoSearchVotes.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isNotEmpty()) {
                    filterVotes(s.toString().toInt())
                    clearText()
                }
            }

        })
    }

    private fun filterVotes(keyWord: Int) {
        val searchVotes: ArrayList<VotesModelItem> = ArrayList()
        for (votesModelItem in votesModel) {
            if (votesModelItem.id.toString().toLowerCase().contains(keyWord.toString().toLowerCase())) {
                searchVotes.add(votesModelItem)
            }
        }
        listVotesAdapter.filterVotes(searchVotes)
    }

    private fun clearText() {
        if (autoSearchVotes.text.toString().isNotEmpty()) {
            imgClearText.visibility = View.VISIBLE
            imgClearText.setOnClickListener {
                autoSearchVotes.setText("")
                setUpViewModel()
                imgClearText.visibility = View.INVISIBLE
                hideKeyboard()
            }
        } else {
            imgClearText.visibility = View.INVISIBLE
        }
    }
}