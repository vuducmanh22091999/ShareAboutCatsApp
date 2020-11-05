package com.example.shareaboutcatsapp.ui.main.account

import android.content.Intent
import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.login.LoginActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.fragment_account
    }

    override fun doViewCreated() {
        showBottomNavigation()
        initListener()
    }

    private fun initListener() {
        tvLogout.setOnClickListener(this)
    }

    private fun backLoginScreen() {
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.tvLogout -> backLoginScreen()
        }
    }
}