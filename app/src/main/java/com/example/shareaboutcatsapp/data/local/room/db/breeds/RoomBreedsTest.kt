package com.example.shareaboutcatsapp.data.local.room.db.breeds

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "breedsTest")
data class RoomBreedsTest(
    @PrimaryKey
    val id: String,
    val name: String,
    val origin: String,
    val description: String,
    val life_span: String,
    val adaptability: Int,
    val affection_level: Int,
    val temperament: String,
    val child_friendly: Int,
    val intelligence: Int,
    val wikipedia_url: String
)