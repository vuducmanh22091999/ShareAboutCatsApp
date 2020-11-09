package com.example.shareaboutcatsapp.ui.main.votes

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.votes.create_votes.CreateVotesFragment
import com.example.shareaboutcatsapp.ui.main.votes.details.DetailsVotesFragment
import kotlinx.android.synthetic.main.fragment_votes.*

class VotesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
        showBottomNavigation()
        initListener()
    }

    private fun initListener() {
        linearCreateMyVotes.setOnClickListener(this)
        tvTestA.setOnClickListener(this)
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
            R.id.tvTestA -> openDetailsVotes()
        }
    }
}