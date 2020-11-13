package com.example.shareaboutcatsapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var myRoomDB: MyRoomDB

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

    fun initMyRoomDB() : MyRoomDB {
        if (!this::myRoomDB.isInitialized) {
            myRoomDB = Room.databaseBuilder(applicationContext, MyRoomDB::class.java, "ShareAboutCatsApp")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
        return myRoomDB
    }
}