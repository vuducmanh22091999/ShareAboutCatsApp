package com.example.shareaboutcatsapp.data.model.favourites

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class FavouritesModelItem(
    @SerializedName("created_at") val created_at: String,
    @SerializedName("id") var id: Int,
    @SerializedName("image") val image: Image,
    @SerializedName("image_id") val image_id: String,
    @SerializedName("sub_id") val sub_id: String,
    @SerializedName("user_id") val user_id: String
) : Serializable