package com.example.shareaboutcatsapp.data.local.room.db.favourites

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image")
data class ImageFavourites(
    @PrimaryKey
    var idImage: Int,
    val urlImage: String
)