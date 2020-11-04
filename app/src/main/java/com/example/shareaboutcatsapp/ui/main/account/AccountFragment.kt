package com.example.shareaboutcatsapp.ui.main.account

import android.content.Intent
import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_account
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        tvLogout.setOnClickListener(this)
    }

    private fun backLoginScreen() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.tvLogout -> backLoginScreen()
        }
    }
}