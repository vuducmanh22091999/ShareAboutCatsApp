package com.example.shareaboutcatsapp.ui.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.shareaboutcatsapp.R
import com.example.shareaboutcatsapp.data.local.share_preferences.AppPreferences
import com.example.shareaboutcatsapp.ui.base.BaseActivity
import com.example.shareaboutcatsapp.ui.main.account.AccountFragment
import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesFragment
import com.example.shareaboutcatsapp.ui.main.home.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    private val homeFragment = HomeFragment()
//    private val votesFragment = VotesFragment()
    private val favouritesFragment = FavouritesFragment()
    private val accountFragment = AccountFragment()
    private lateinit var fragmentManager: FragmentManager
    private lateinit var currentFragment: Fragment
    private lateinit var appPreferences: AppPreferences

    override fun getLayoutID(): Int {
        return R.layout.activity_main
    }

    override fun doViewCreated() {
        appPreferences = AppPreferences(this)
        overridePendingTransition(R.anim.slide_blink, R.anim.slide_blink)
        showBottomNavigation()
        setUpFragment()
//        handleNavigationBottom()
        initMyRoomDB()
    }

    fun checkWifi(): Boolean {
        return appPreferences.getConnect()
    }

    private fun handleNavigationBottom() {
        bottomNavigation.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .show(homeFragment).hide(currentFragment)
                        .commit()
                    currentFragment = homeFragment
                }
//                R.id.navigation_votes -> {
//                    fragmentManager.beginTransaction().show(votesFragment).hide(currentFragment)
//                        .commit()
//                    currentFragment = votesFragment
//                }
                R.id.favouritesFragment -> {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .show(favouritesFragment)
                        .hide(currentFragment).commit()
                    currentFragment = favouritesFragment
                }
                R.id.accountFragment -> {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                        .show(accountFragment).hide(currentFragment)
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
//        fragmentManager.beginTransaction().add(R.id.flContentScreens, homeFragment).commit()
////        fragmentManager.beginTransaction().show(homeFragment).commit()
////        fragmentManager.beginTransaction().add(R.id.flContentScreens, votesFragment).commit()
////        fragmentManager.beginTransaction().hide(votesFragment).commit()
//        fragmentManager.beginTransaction().add(R.id.flContentScreens, favouritesFragment).commit()
//        fragmentManager.beginTransaction().hide(favouritesFragment).commit()
//        fragmentManager.beginTransaction().add(R.id.flContentScreens, accountFragment).commit()
//        fragmentManager.beginTransaction().hide(accountFragment).commit()

//        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
//        val navController = findNavController(R.id.flContentScreens)
//        bottomNavigationView.setupWithNavController(navController)

        //navigation sẽ replace lại các fragment..
        //khi từ màn hình FullImage back về DetailsCategories thì sẽ là replace
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigation)
        val navHostFragment = fragmentManager.findFragmentById(R.id.flContentScreens) as NavHostFragment
        val nav = navHostFragment.navController
//        val navigator = KeepStateNavigator(this, navHostFragment.childFragmentManager, R.id.flContentScreens)
//        nav.navigatorProvider += navigator

//        nav.setGraph(R.navigation.my_nav)
        bottomNavigationView.setupWithNavController(nav)

        //có 3 fragment: 1-2-3 từ 3 muốn về thẳng 1 thì qua file navigation
        //click vô mũi tên từ 2-3 chọn popUpTo rồi chọn fragment 1
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