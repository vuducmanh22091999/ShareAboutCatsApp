package com.example.shareaboutcatsapp.data.local.room.db.breeds

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "roomWeight")
data class RoomWeight(
    @NotNull
    @PrimaryKey(autoGenerate = true)
    var idWeight: Int = 0,

    val imperial: String,
    val metric: String
)