package com.example.shareaboutcatsapp.ui.main.votes

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
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
    private val dataList = ArrayList<VotesModelItem>()
    private var dataListVotes: ArrayList<String> = ArrayList()
    lateinit var dialog: Dialog

    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
//        showLoading()
        showBottomNavigation()
        initListener()
        setUpViewModel()
    }
    private fun initListener() {
        linearCreateMyVotes.setOnClickListener(this)
    }

    private fun setUpViewModel() {
        votesViewModel.getVotes(getString(R.string.x_api_key))
        votesViewModel.votes.observe(this, {
            setUpRecyclerViewListVotes(it)
//            hideLoading()
        })

        votesViewModel.votes.observe(this, {
            dataListVotes.clear()
            it.forEach { votesModelItem ->
                dataListVotes.add((votesModelItem.id.toString()))
            }
            val arrayAdapter = ArrayAdapter<String>(
                context!!,
                android.R.layout.simple_spinner_dropdown_item,
                dataListVotes
            )
            autoSearchVotes.setAdapter(arrayAdapter)
        })
    }

    private fun detailsVotes(index: Int) {
        val detailsVotesFragment = DetailsVotesFragment()
        val bundle = Bundle()
        val votesModelItem = votesViewModel.votes.value?.get(index)
        bundle.putSerializable("detailsVotes", votesModelItem)
        detailsVotesFragment.arguments = bundle
        addFragment(detailsVotesFragment, R.id.flContentScreens)
    }

    private fun setUpRecyclerViewListVotes(votesModel: VotesModel) {
        listVotesAdapter = ListVotesAdapter(votesModel, {
            detailsVotes(it)
        },
            { i: Int, s: Int -> openDialogDelete(s) })
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
            Toast.makeText(context, "No", Toast.LENGTH_SHORT).show()
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

    override fun onClick(v: View) {
        when (v.id) {
            R.id.linearCreateMyVotes -> openCreateVotesScreen()
        }
    }
}