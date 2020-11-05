package com.example.shareaboutcatsapp.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.login.LoginActivity

class SplashActivity : BaseActivity() {
    private val SPLASH_DISPLAY_LENGTH : Long = 4800
    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun doViewCreated() {
        openNewScreens()
    }

    private fun openNewScreens() {
        Handler().postDelayed({
            val intentNewScreen = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(intentNewScreen)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    override fun onBackPressed() {
        finish()
    }
}