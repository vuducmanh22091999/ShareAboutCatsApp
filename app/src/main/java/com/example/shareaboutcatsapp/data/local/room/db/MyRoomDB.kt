package com.example.shareaboutcatsapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shareaboutcatsapp.data.local.room.dao.DAOBreeds
import com.example.shareaboutcatsapp.data.local.room.db.account.RoomAccount
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories
import com.example.shareaboutcatsapp.data.local.room.db.favourites.RoomFavourites
import com.example.shareaboutcatsapp.data.local.room.db.votes.RoomVotes

@Database(
    entities = [RoomBreeds::class, RoomCategories::class, RoomFavourites::class, RoomAccount::class, RoomVotes::class],
    version = 1
)
abstract class MyRoomDB : RoomDatabase() {
    abstract val getDAOBreeds: DAOBreeds
}