package com.example.shareaboutcatsapp.di

import androidx.room.Room
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB
import com.example.shareaboutcatsapp.data.repository.*
import com.example.shareaboutcatsapp.ui.MyApplication
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {
    factory { CategoriesRepo(get()) }
    factory { FavouritesRepo(get()) }
    factory { BreedsRepo(get()) }
    factory { VotesRepo(get()) }
    single { provideAppDatabase(androidApplication() as MyApplication) }

}

fun provideAppDatabase(app: MyApplication): MyRoomDB {
    return Room.databaseBuilder(app, MyRoomDB::class.java, "ShareAboutCatsApp")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build()
}