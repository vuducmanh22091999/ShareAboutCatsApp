package com.example.shareaboutcatsapp.data.model.votes

import com.google.gson.annotations.SerializedName

data class CreateVotes(
    @SerializedName("id") val id: Int,
    @SerializedName("message") val message: String,
)