package com.example.shareaboutcatsapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.shareaboutcatsapp.data.local.room.db.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.RoomWeight
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem

@Dao
interface DAOBreeds {
    @Insert
    fun insertBreeds(roomBreeds: RoomBreeds)

    @Query("Select * From breeds")
    fun getBreeds() : MutableList<RoomBreeds>
}