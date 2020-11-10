package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.data.repository.BreedsRepo
import com.example.shareaboutcatsapp.data.repository.CategoriesRepo
import com.example.shareaboutcatsapp.data.repository.FavouritesRepo
import com.example.shareaboutcatsapp.data.repository.VotesRepo
import org.koin.dsl.module

val repositoryModule = module {
    single { CategoriesRepo(get()) }
    single { FavouritesRepo(get()) }
    single { BreedsRepo(get()) }
    single { VotesRepo(get()) }
}