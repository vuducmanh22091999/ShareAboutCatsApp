package com.example.shareaboutcatsapp.ui.main

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.account.AccountFragment
import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesFragment
import com.example.shareaboutcatsapp.ui.main.home.HomeFragment
import com.example.shareaboutcatsapp.ui.main.votes.VotesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    var fragmentSelected: Fragment = Fragment()
    lateinit var dialog: AlertDialog

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun doViewCreated() {
        showBottomNavigation()
        addFragment(HomeFragment(), R.id.flContentScreens)
        handleNavigationBottom()
    }

    private fun handleNavigationBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            fragmentSelected = when (it.itemId) {
                R.id.navigation_home -> HomeFragment()
                R.id.navigation_votes -> VotesFragment()
                R.id.navigation_favourites -> FavouritesFragment()
                R.id.navigation_account -> AccountFragment()
                else -> fragmentSelected
            }
            addFragment(fragmentSelected, R.id.flContentScreens)
            true
        }
    }

    fun hideBottomNavigation() {
        bottomNavigation.visibility = View.GONE
    }

    fun showBottomNavigation() {
        bottomNavigation.visibility = View.VISIBLE
    }

    override fun onBackPressed() {
        finish()
    }

//    fun showLoading() {
//        progressBarLoading.visibility = View.VISIBLE
//    }
//
//    fun hideLoading() {
//        progressBarLoading.visibility = View.INVISIBLE
//    }

    fun showLoading() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        val layoutInflater: LayoutInflater = this.layoutInflater
        builder.setView(layoutInflater.inflate(R.layout.dialog_loading, null))
        builder.setCancelable(true)

        dialog = builder.create()
        dialog.show()
    }

    fun hideLoading() {
        dialog.dismiss()
    }

}