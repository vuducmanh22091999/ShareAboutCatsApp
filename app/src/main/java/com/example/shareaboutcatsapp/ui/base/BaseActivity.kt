package com.example.shareaboutcatsapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseActivity: AppCompatActivity() {
    abstract fun getLayoutID(): Int
    abstract fun doViewCreated()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        doViewCreated()
    }

    fun addFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .add(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, id: Int) {
        supportFragmentManager.beginTransaction()
            .replace(id, fragment, fragment::class.java.simpleName)
            .addToBackStack(null)
            .commit()
    }
}