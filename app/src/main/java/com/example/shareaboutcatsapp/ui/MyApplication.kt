package com.example.shareaboutcatsapp.ui

import android.app.Application
import com.example.shareaboutcatsapp.di.appModule
import com.example.shareaboutcatsapp.di.repositoryModule
import com.example.shareaboutcatsapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@MyApplication)
            modules(listOf(appModule, repositoryModule, viewModelModule))
        }
    }
}