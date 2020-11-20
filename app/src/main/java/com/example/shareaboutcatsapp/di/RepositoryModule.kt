package com.example.shareaboutcatsapp.di

import com.example.shareaboutcatsapp.data.repository.*
import org.koin.dsl.module

val repositoryModule = module {
    single { CategoriesRepo(get()) }
    single { FavouritesRepo(get()) }
    single { BreedsRepo(get()) }
    single { VotesRepo(get()) }
    single { RoomRepo(get()) }
}