package com.example.shareaboutcatsapp.data.local.room.db.breeds

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shareaboutcatsapp.data.model.breeds.BreedsTest

@Entity(tableName = "roomImageTest")
data class RoomImageBreedsTest(
    @PrimaryKey
    val id: String,
    val url: String,
    @Embedded
    val roomBreedsTest: List<BreedsTest>
)