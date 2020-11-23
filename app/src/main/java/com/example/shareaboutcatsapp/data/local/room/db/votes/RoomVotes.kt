package com.example.shareaboutcatsapp.data.local.room.db.votes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "votes")
data class RoomVotes(
    @PrimaryKey
    val idVotes: Int,
    val countryCodeVotes: String? = null,
    val createdAtBreeds: String? = null,
    val imageIDVotes: String? = null,
    val subIDVotes: String? = null,
    val valueBreeds: Int? = null
)