package com.example.shareaboutcatsapp.data.local.room.db.favourites

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class RoomFavourites(
    @PrimaryKey
    var idFavourites: Int,
    val subIDFavourites: Int,
    val userIDFavourites: Int,
    val createAtFavourites: String,
    val imageIDFavourites: String,
    @Embedded val imageFavourites: ImageFavourites? = null
)