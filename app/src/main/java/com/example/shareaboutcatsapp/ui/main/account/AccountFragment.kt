package com.example.shareaboutcatsapp.ui.main.account

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.ui.base.BaseFragment
import com.example.shareaboutcatsapp.ui.login.LoginActivity
import com.example.shareaboutcatsapp.ui.main.MainActivity
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_account.*

class AccountFragment : BaseFragment(), View.OnClickListener {
    private lateinit var appPreferences: AppPreferences

    override fun getLayoutID(): Int {
        return R.layout.fragment_account
    }

    override fun doViewCreated() {
        appPreferences = context?.let { AppPreferences(it) }!!

        showBottomNavigation()
        initListener()
        setProfile()
    }

    private fun checkWifi() {
        if ((activity as MainActivity).checkWifi()) {
            backLoginScreen()
        } else {
            context?.let { Toasty.error(it, "Turn on Wifi to log out", Toast.LENGTH_SHORT).show() }
        }
    }

    private fun initListener() {
        tvLogout.setOnClickListener(this)
    }

    private fun setProfile() {
        tvEmail.text = appPreferences.getLoginEmail()
        tvUserName.text = appPreferences.getLoginUserName()
        tvPhone.text = appPreferences.getLoginPhoneNumber()
        if (getIDUserFacebook().isNotEmpty()) {
            context?.let {
                Glide.with(it).load(urlAvatar()).placeholder(R.drawable.ic_account).into(imgAvatar)
            }
        } else {
            context?.let {
                Glide.with(it).load(appPreferences.getLoginAvatar())
                    .placeholder(R.drawable.ic_account).into(imgAvatar)
            }
        }

    }

    private fun getIDUserFacebook(): String {
        var facebookUserId = ""
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            for (profile in user.providerData) {
                if (FacebookAuthProvider.PROVIDER_ID == profile.providerId) {
                    facebookUserId = profile.uid
                }
            }
        }
        return facebookUserId
    }

    private fun urlAvatar(): String {
        return "https://graph.facebook.com/${getIDUserFacebook()}/picture?type=large"
    }

    private fun backLoginScreen() {
        Firebase.auth.signOut()
        val intent = Intent(activity, LoginActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun showBottomNavigation() {
        if (activity is MainActivity) {
            (activity as MainActivity).showBottomNavigation()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvLogout -> checkWifi()
        }
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("AAA", "onDetachAccount")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AAA", "onDestroyAccount")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("AAA", "onDestroyViewAccount")
    }
}