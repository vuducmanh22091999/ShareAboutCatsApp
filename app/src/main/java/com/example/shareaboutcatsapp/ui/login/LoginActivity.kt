package com.example.shareaboutcatsapp.ui.login

import android.content.Intent
import android.view.View
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutID(): Int {
        return R.layout.activity_login
    }

    override fun doViewCreated() {
        initListener()
    }

    private fun initListener() {
        linearLoginWithFacebook.setOnClickListener(this)
        linearLoginWithGoogle.setOnClickListener(this)
    }

    private fun loginWithFacebook() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun loginWithGoogle() {

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.linearLoginWithFacebook -> loginWithFacebook()
            R.id.linearLoginWithGoogle -> loginWithGoogle()
        }
    }

    override fun onBackPressed() {
        finish()
    }
}