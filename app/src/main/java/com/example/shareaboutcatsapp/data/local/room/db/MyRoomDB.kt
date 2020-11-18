package com.example.shareaboutcatsapp.data.local.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shareaboutcatsapp.data.local.room.dao.DAOBreeds
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds

@Database(entities = [RoomBreeds::class], version = 1)
abstract class MyRoomDB : RoomDatabase() {
    abstract val getDAOBreeds: DAOBreeds
}