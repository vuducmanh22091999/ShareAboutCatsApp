package com.example.shareaboutcatsapp.data.model.votes

import com.google.gson.annotations.SerializedName

data class NotificationDelete(
    @SerializedName("message") val message: String
)