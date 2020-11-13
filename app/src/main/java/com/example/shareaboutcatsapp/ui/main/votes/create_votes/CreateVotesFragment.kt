package com.example.shareaboutcatsapp.ui.main.votes.create_votes

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_create_your_votes.*

class CreateVotesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_create_your_votes
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
    }

    private fun initListener() {
        imgBackMyVotes.setOnClickListener(this)
    }

    private fun backToMyVotes() {
        parentFragmentManager.popBackStack()
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBackMyVotes -> backToMyVotes()
        }
    }
}