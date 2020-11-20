package com.example.shareaboutcatsapp.ui.splash

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.login.LoginActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashActivity : AppCompatActivity() {
    private val SPLASH_DISPLAY_LENGTH: Long = 4800
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        auth = Firebase.auth
        openNewScreens()
        checkWifi()
    }

    private fun openNewScreens() {
        val user = auth.currentUser
        if (user != null) {
            Handler().postDelayed({
                val intentMainScreen = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intentMainScreen)
                finish()
            }, SPLASH_DISPLAY_LENGTH)
        }
        else {
            Handler().postDelayed({
                val intentNewScreen = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(intentNewScreen)
                finish()
            }, SPLASH_DISPLAY_LENGTH)
        }
    }

    private fun checkWifi() {
        val connectivityManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)

        if (networkInfo != null) {
            if (networkInfo.isConnected) {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onBackPressed() {
        finish()
    }
}