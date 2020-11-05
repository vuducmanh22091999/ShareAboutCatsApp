package com.example.shareaboutcatsapp.ui.main.votes.details

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_details_votes.*

class DetailsVotesFragment: BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_votes
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
    }

    private fun initListener() {
        imgBackVotes.setOnClickListener(this)
    }

    private fun backToVotes() {
        activity?.onBackPressed()
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBackVotes -> backToVotes()
        }
    }
}