package com.example.shareaboutcatsapp.ui.main.votes

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
import kotlinx.android.synthetic.main.fragment_votes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VotesFragment : BaseFragment(), View.OnClickListener {
    private val votesViewModel: VotesViewModel by viewModel()
    private lateinit var listVotesAdapter: ListVotesAdapter
    private val dataList = ArrayList<VotesModelItem>()
    private var dataListVotes: ArrayList<String> = ArrayList()

    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
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
        replaceFragment(detailsVotesFragment, R.id.flContentScreens)
    }

    private fun setUpRecyclerViewListVotes(votesModel: VotesModel) {
        listVotesAdapter = ListVotesAdapter(votesModel, {
            detailsVotes(it)
        }, { Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show() })
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListMyVotes.setHasFixedSize(true)
        rcvListMyVotes.layoutManager = linearLayoutManager
        rcvListMyVotes.adapter = listVotesAdapter
    }

    private fun openCreateVotesScreen() {
        replaceFragment(CreateVotesFragment(), R.id.flContentScreens)
    }

    private fun openDetailsVotes() {
        replaceFragment(DetailsVotesFragment(), R.id.flContentScreens)
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