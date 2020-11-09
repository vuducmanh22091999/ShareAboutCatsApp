package com.example.shareaboutcatsapp.ui.splash

import android.content.Intent
import android.os.Handler
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.login.LoginActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : BaseActivity() {
    private val SPLASH_DISPLAY_LENGTH: Long = 4800
    private lateinit var auth: FirebaseAuth

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun doViewCreated() {
        auth = Firebase.auth

        openNewScreens()
    }

    private fun openNewScreens() {
        val user = auth.currentUser
        if (user != null) {
            val intentMainScreen = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intentMainScreen)
            finish()
        }
        else {
            Handler().postDelayed({
                val intentNewScreen = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intentNewScreen)
                finish()
            }, SPLASH_DISPLAY_LENGTH)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}