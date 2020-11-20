package com.example.shareaboutcatsapp.data.local.room.db.categories

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class RoomCategories(
    @PrimaryKey
    var idCategories: Int,
    val nameCategories: String
)