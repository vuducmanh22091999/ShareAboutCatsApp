package com.example.shareaboutcatsapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shareaboutcatsapp.data.local.room.dao.DAOBreeds
import com.example.shareaboutcatsapp.data.local.room.db.account.RoomAccount
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomImageBreedsTest
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories
import com.example.shareaboutcatsapp.data.local.room.db.favourites.RoomFavourites
import com.example.shareaboutcatsapp.data.local.room.db.image.RoomImage
import com.example.shareaboutcatsapp.data.local.room.db.votes.RoomVotes

@Database(
    entities = [RoomBreeds::class, RoomCategories::class, RoomFavourites::class, RoomAccount::class,
        RoomVotes::class, RoomImage::class],
    version = 2,
    exportSchema = false
)
abstract class MyRoomDB : RoomDatabase() {
    abstract val getDAOBreeds: DAOBreeds
}