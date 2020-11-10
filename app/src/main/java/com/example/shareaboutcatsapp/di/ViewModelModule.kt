package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get(), get(), get()) }
}