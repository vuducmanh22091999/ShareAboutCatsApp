package com.example.shareaboutcatsapp.data.model.breeds

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class ImageBreedsModelItemTest(
    @Embedded val breedsTest: BreedsTest,
    @SerializedName("id") val idImage: String,
    @SerializedName("url") val url: String
)