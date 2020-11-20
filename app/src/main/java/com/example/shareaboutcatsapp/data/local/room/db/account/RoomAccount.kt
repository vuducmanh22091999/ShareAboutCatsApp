package com.example.shareaboutcatsapp.data.local.room.db.account

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class RoomAccount(
    @PrimaryKey (autoGenerate = true)
    var idAccount: Int,
    val userNameAccount: String,
    val emailAccount: String,
    val phoneNumberAccount: String,
    val avatarAccount: String
)