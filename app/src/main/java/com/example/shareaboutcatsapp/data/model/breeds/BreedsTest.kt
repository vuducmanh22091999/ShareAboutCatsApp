package com.example.shareaboutcatsapp.data.model.breeds

import com.google.gson.annotations.SerializedName

data class BreedsTest(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("description") val description: String,
    @SerializedName("life_span") val life_span: String,
    @SerializedName("adaptability") val adaptability: Int,
    @SerializedName("affection_level") val affection_level: Int,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("child_friendly") val child_friendly: Int,
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("wikipedia_url") val wikipedia_url: String
)
