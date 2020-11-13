package com.example.shareaboutcatsapp.data.model.categories

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoriesModelItem(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
) : Serializable