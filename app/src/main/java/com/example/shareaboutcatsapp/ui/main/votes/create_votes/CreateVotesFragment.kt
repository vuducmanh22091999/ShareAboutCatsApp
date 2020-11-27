package com.example.shareaboutcatsapp.ui.main.votes.create_votes

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_create_your_votes.*

class CreateVotesFragment : BaseFragment(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    override fun getLayoutID(): Int {
        return R.layout.fragment_create_your_votes
    }

    override fun doViewCreated() {
        auth = Firebase.auth

        hideBottomNavigation()
        initListener()
        createVotes()
    }

    private fun initListener() {
//        imgBackMyVotes.setOnClickListener(this)
    }

    private fun createVotes() {
        val user = auth.currentUser
        val subID = user?.uid
        etSubID.setText(subID)
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
//            R.id.imgBackMyVotes -> backToMyVotes()
        }
    }
}