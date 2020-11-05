package com.example.shareaboutcatsapp.ui.main.home

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.chat.ListChatFragment
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_home
    }

    override fun doViewCreated() {
        showBottomNavigation()
        initListener()
    }

    private fun initListener() {
        imgChat.setOnClickListener(this)
        tvListCategories.setOnClickListener(this)
    }

    private fun openListChat() {
        replaceFragment(ListChatFragment(), R.id.flContentScreens)
    }

    private fun openDetailsBreeds() {
        replaceFragment(DetailsBreedsFragment(), R.id.flContentScreens)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgChat -> openListChat()
            R.id.tvListCategories -> openDetailsBreeds()
        }
    }
}