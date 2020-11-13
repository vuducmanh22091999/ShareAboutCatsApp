package com.example.shareaboutcatsapp.data.local.room.dao

import androidx.room.Insert
import androidx.room.Query
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem

interface DAOBreeds {
    @Insert
    fun insertBreeds(breedsModelItem: BreedsModelItem)

    @Query("Select * From breeds")
    fun getBreeds() : List<String>
}