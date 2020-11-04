package com.example.shareaboutcatsapp.ui.main.chat

import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_list_chat.*

class ListChatFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_list_chat
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        imgBack.setOnClickListener(this)
    }

    private fun backToHome() {
        activity?.onBackPressed()
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.imgBack -> backToHome()
        }
    }
}