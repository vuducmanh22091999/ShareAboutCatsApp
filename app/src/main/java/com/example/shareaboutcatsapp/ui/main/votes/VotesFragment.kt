package com.example.shareaboutcatsapp.ui.main.votes

import android.view.View
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.votes.adapter.ListVotesAdapter
import com.example.shareaboutcatsapp.ui.main.votes.create_votes.CreateVotesFragment
import com.example.shareaboutcatsapp.ui.main.votes.details.DetailsVotesFragment
import kotlinx.android.synthetic.main.fragment_votes.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class VotesFragment : BaseFragment(), View.OnClickListener {
    private val votesViewModel:VotesViewModel by viewModel()
    private lateinit var listVotesAdapter: ListVotesAdapter
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
            it.forEach {votesModelItem ->
                dataListVotes.add((votesModelItem.id.toString()))
            }
            val arrayAdapter = ArrayAdapter<String>(context!!, android.R.layout.simple_spinner_dropdown_item, dataListVotes)
            autoSearchVotes.setAdapter(arrayAdapter)
        })
    }

    private fun setUpRecyclerViewListVotes(votesModel: VotesModel) {
        listVotesAdapter = ListVotesAdapter(votesModel)
        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        rcvListMyVotes.setHasFixedSize(true)
        rcvListMyVotes.layoutManager = linearLayoutManager
        rcvListMyVotes.adapter = listVotesAdapter
        rcvListMyVotes.postDelayed({rcvListMyVotes.hideShimmerAdapter()},30000)
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
        when(v.id) {
            R.id.linearCreateMyVotes -> openCreateVotesScreen()
        }
    }
}