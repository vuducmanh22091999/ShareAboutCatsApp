package com.example.shareaboutcatsapp.ui.main

import android.app.AlertDialog
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.room.Room
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.account.AccountFragment
import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesFragment
import com.example.shareaboutcatsapp.ui.main.home.HomeFragment
import com.example.shareaboutcatsapp.ui.main.votes.VotesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val homeFragment = HomeFragment()
    private val votesFragment = VotesFragment()
    private val favouritesFragment = FavouritesFragment()
    private val accountFragment = AccountFragment()
    lateinit var fragmentManager: FragmentManager
    lateinit var currentFragment: Fragment
    private lateinit var appPreferences: AppPreferences

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun doViewCreated() {
        appPreferences = AppPreferences(this)
        showBottomNavigation()
        setUpFragment()
        handleNavigationBottom()
        initMyRoomDB()
    }

    fun checkWifi(): Boolean? {
        return appPreferences.getConnect()
    }

    private fun handleNavigationBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    fragmentManager.beginTransaction().show(homeFragment).hide(currentFragment)
                        .commit()
                    currentFragment = homeFragment
                }
                R.id.navigation_votes -> {
                    fragmentManager.beginTransaction().show(votesFragment).hide(currentFragment)
                        .commit()
                    currentFragment = votesFragment
                }
                R.id.navigation_favourites -> {
                    fragmentManager.beginTransaction().show(favouritesFragment)
                        .hide(currentFragment).commit()
                    currentFragment = favouritesFragment
                }
                R.id.navigation_account -> {
                    fragmentManager.beginTransaction().show(accountFragment).hide(currentFragment)
                        .commit()
                    currentFragment = accountFragment
                }
            }
            true
        }
    }

    private fun setUpFragment() {
        fragmentManager = supportFragmentManager
        currentFragment = homeFragment


        fragmentManager.beginTransaction().add(R.id.flContentScreens, homeFragment).commit()
        fragmentManager.beginTransaction().show(homeFragment).commit()
        fragmentManager.beginTransaction().add(R.id.flContentScreens, votesFragment).commit()
        fragmentManager.beginTransaction().hide(votesFragment).commit()
        fragmentManager.beginTransaction().add(R.id.flContentScreens, favouritesFragment).commit()
        fragmentManager.beginTransaction().hide(favouritesFragment).commit()
        fragmentManager.beginTransaction().add(R.id.flContentScreens, accountFragment).commit()
        fragmentManager.beginTransaction().hide(accountFragment).commit()
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

}