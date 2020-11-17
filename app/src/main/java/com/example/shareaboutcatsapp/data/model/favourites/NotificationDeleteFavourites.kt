package com.example.shareaboutcatsapp.data.model.favourites

import com.google.gson.annotations.SerializedName

data class NotificationDeleteFavourites(
    @SerializedName("level") val level: String,
    @SerializedName("message") val message: String,
    @SerializedName("status") val status: Int
)