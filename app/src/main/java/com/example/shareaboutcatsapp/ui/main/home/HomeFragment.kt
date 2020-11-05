package com.example.shareaboutcatsapp.ui.main.home

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.chat.ListChatFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        imgChat.setOnClickListener(this)
        tvListCategories.setOnClickListener(this)
    }

    private fun openListChat() {
        addFragment(ListChatFragment(), R.id.flContentScreens)
    }

    private fun openDetailsBreeds() {
        addFragment(DetailsBreedsFragment(), R.id.flContentScreens)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgChat -> openListChat()
            R.id.tvListCategories -> openDetailsBreeds()
        }
    }
}