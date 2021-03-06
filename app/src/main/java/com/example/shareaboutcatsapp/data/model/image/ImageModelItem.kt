package com.example.shareaboutcatsapp.data.model.image

import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageModelItem(
    @SerializedName("categories") val categoriesModelItem: ArrayList<CategoriesModelItem>,
    @SerializedName("url") val url: String,
    @SerializedName("id") val id: String
) : Serializable