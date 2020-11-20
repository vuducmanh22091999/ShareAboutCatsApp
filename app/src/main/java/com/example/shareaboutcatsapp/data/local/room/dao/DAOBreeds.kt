package com.example.shareaboutcatsapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories

@Dao
interface DAOBreeds {
    @Insert
    fun insertBreeds(roomBreeds: RoomBreeds)

    @Query("Select name From breeds")
    fun getBreeds() : MutableList<String>

    @Insert
    fun insertCategories(roomCategories: RoomCategories)

    @Query("Select nameCategories From categories")
    fun getCategories() : MutableList<String>
}