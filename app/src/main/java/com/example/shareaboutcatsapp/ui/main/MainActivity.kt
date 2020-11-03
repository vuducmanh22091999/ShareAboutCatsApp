package com.example.shareaboutcatsapp.ui.main

import androidx.fragment.app.Fragment
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.account.AccountFragment
import com.example.shareaboutcatsapp.ui.main.breeds.DetailsBreedsFragment
import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesFragment
import com.example.shareaboutcatsapp.ui.main.home.HomeFragment
import com.example.shareaboutcatsapp.ui.main.votes.VotesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    var fragmentSelected: Fragment = Fragment()

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun doViewCreated() {
        addFragment(HomeFragment(), R.id.flContentScreens)
        handleNavigationBottom()
    }

    private fun handleNavigationBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            fragmentSelected = when (it.itemId) {
//                R.id.navigation_home -> HomeFragment()
                R.id.navigation_home -> DetailsBreedsFragment()
                R.id.navigation_votes -> VotesFragment()
                R.id.navigation_favourites -> FavouritesFragment()
                R.id.navigation_account -> AccountFragment()
                else -> fragmentSelected
            }
            addFragment(fragmentSelected, R.id.flContentScreens)
            true
        }
    }
}