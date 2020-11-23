package com.example.shareaboutcatsapp.data.local.room.db.favourites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shareaboutcatsapp.data.model.favourites.Image

@Entity(tableName = "favourites")
data class RoomFavourites(
    @PrimaryKey
    var idFavourites: Int,
    val subIDFavourites: String,
    val userIDFavourites: String,
    val createAtFavourites: String,
    val imageIDFavourites: String,
    @Embedded val imageFavourites: Image? = null
)