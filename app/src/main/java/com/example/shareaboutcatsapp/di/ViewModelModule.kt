package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesViewModel
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import com.example.shareaboutcatsapp.ui.main.votes.VotesViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single { HomeViewModel(get(), get(), get()) }
    single { VotesViewModel(get()) }
    single { FavouritesViewModel(get()) }
}