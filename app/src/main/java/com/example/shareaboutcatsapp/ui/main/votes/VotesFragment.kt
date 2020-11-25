package com.example.shareaboutcatsapp.ui.main.votes

import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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
import com.google.gson.Gson
import kotlinx.android.synthetic.main.dialog_delete.*
import kotlinx.android.synthetic.main.fragment_votes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VotesFragment : BaseFragment(), View.OnClickListener {
    private val votesViewModel: VotesViewModel by viewModel()
    private lateinit var listVotesAdapter: ListVotesAdapter
    private var dataListVotes: ArrayList<String> = ArrayList()
    lateinit var dialog: Dialog
    var votesModel = VotesModel()
    var limit = 10
    var page = 1

    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
        checkWifi()
        showBottomNavigation()
        initListener()
        setUpViewModel()
        searchVotes()
    }

    private fun initListener() {
        linearCreateMyVotes.setOnClickListener(this)
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi() == true) {
            callApi()
        } else {
            votesViewModel.getDataVotes()
        }
    }

    private fun callApi() {
        votesViewModel.getVotes(getString(R.string.x_api_key), limit, page)
    }

    private fun setUpViewModel() {
        votesViewModel.votes.observe(this, {
            votesViewModel.saveDataVotes(it)
            setUpRecyclerViewListVotes(it)
        })

//        votesViewModel.votes.observe(this, {
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
        val votesModelItem = votesViewModel.votes.value?.find { it.id == id }
        bundle.putSerializable("detailsVotes", votesModelItem)
        detailsVotesFragment.arguments = bundle
        addFragment(detailsVotesFragment, R.id.flContentScreens)
    }

    private fun setUpRecyclerViewListVotes(votesModel: VotesModel) {
        this.votesModel = votesModel
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

        dialog.linearYes.setOnClickListener {
            deleteVotes(voteID)
            dialog.dismiss()
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }

        dialog.linearNo.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
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
            if (votesModelItem.id.toString().contains(keyWord.toString())) {
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.linearCreateMyVotes -> openCreateVotesScreen()
//            R.id.imgSearchVotes -> searchVotes(autoSearchVotes.text.toString().toInt())
        }
    }
}