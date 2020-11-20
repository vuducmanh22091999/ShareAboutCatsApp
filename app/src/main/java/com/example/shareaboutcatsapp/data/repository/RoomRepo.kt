package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.local.room.dao.DAOBreeds
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories

data class RoomRepo(private val daoBreeds: DAOBreeds) {
    suspend fun insertCategories(roomCategories: RoomCategories) = daoBreeds.insertCategories(roomCategories)
}