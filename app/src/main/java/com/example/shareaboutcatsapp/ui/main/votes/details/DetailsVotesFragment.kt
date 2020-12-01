package com.example.shareaboutcatsapp.ui.main.votes.details

import android.annotation.SuppressLint
import android.view.View
import android.widget.Toast
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_details_votes.*

class DetailsVotesFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_details_votes
    }

    override fun doViewCreated() {
        hideBottomNavigation()
        initListener()
        getDataDetailsVotes()
    }

    private fun initListener() {
        imgBackVotes.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n")
    private fun getDataDetailsVotes() {
        val bundle = arguments
        bundle?.getSerializable("detailsVotes")?.let {
            val votesModelItem = it as VotesModelItem
            val id = votesModelItem.id.toString()
            val imageID = votesModelItem.image_id
            val value = votesModelItem.value.toString()
            val createdAt = votesModelItem.created_at
            val date = createdAt
            val time = createdAt
            val formatDate = date.substring(0,10)
            val formatTime = time.substring(11,19)

            tvIDVotes.text = id
            tvImageIDVotes.text = imageID
            tvSubIDVotes.text = "null"
            tvValueVotes.text = value
            tvCountryCodeVotes.text = "null"
            tvCreateAtVotes.text = "$formatDate $formatTime"
        }
    }

    private fun backToVotes() {
        parentFragmentManager.popBackStack()
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    private fun hideBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).hideBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.imgBackVotes -> backToVotes()
        }
    }
}