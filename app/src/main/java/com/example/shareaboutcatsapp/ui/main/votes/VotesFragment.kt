package com.example.shareaboutcatsapp.ui.main.votes

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.votes.create_votes.CreateVotesFragment
import kotlinx.android.synthetic.main.fragment_votes.*

class VotesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_votes
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        linearCreateMyVotes.setOnClickListener(this)
    }

    private fun openCreateVotesScreen() {
        addFragment(CreateVotesFragment(), R.id.flContentScreens)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.linearCreateMyVotes -> openCreateVotesScreen()
        }
    }
}