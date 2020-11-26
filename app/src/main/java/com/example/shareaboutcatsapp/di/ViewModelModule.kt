package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.ui.main.favourites.FavouritesViewModel
import com.example.shareaboutcatsapp.ui.main.home.HomeViewModel
import com.example.shareaboutcatsapp.ui.main.votes.VotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get(), get(), get()) }
    viewModel { VotesViewModel(get(), get()) }
    viewModel { FavouritesViewModel(get(), get()) }
}