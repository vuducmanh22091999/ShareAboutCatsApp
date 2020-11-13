package com.example.shareaboutcatsapp.data.model.votes

import com.google.gson.annotations.SerializedName

data class CreateVotes(
    @SerializedName("image_id") val image_id: String,
    @SerializedName("sub_id") val sub_id: String,
    @SerializedName("value") val value: Int
)