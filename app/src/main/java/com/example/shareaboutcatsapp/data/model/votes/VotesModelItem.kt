package com.example.shareaboutcatsapp.data.model.votes

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class VotesModelItem(
    @SerializedName("country_code") val country_code: String,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("id") val id: Int,
    @SerializedName("image_id") val image_id: String,
    @SerializedName("sub_id") val sub_id: String,
    @SerializedName("value") val value: Int
) : Serializable