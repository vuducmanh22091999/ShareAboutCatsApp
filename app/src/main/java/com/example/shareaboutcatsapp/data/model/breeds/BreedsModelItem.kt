package com.example.shareaboutcatsapp.data.model.breeds

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BreedsModelItem(
    @SerializedName("adaptability") val adaptability: Int,
    @SerializedName("affection_level") val affection_level: Int,
    @SerializedName("alt_names") val alt_names: String,
    @SerializedName("bidability") val bidability: Int,
    @SerializedName("cat_friendly") val cat_friendly: Int,
    @SerializedName("cfa_url") val cfa_url: String,
    @SerializedName("child_friendly") val child_friendly: Int,
    @SerializedName("country_code") val country_code: String,
    @SerializedName("country_codes") val country_codes: String,
    @SerializedName("description") val description: String,
    @SerializedName("dog_friendly") val dog_friendly: Int,
    @SerializedName("energy_level") val energy_level: Int,
    @SerializedName("experimental") val experimental: Int,
    @SerializedName("grooming") val grooming: Int,
    @SerializedName("hairless") val hairless: Int,
    @SerializedName("health_issues") val health_issues: Int,
    @SerializedName("hypoallergenic") val hypoallergenic: Int,
    @SerializedName("id") val id: String,
    @SerializedName("indoor") val indoor: Int,
    @SerializedName("intelligence") val intelligence: Int,
    @SerializedName("lap") val lap: Int,
    @SerializedName("life_span") val life_span: String,
    @SerializedName("name") val name: String,
    @SerializedName("natural") val natural: Int,
    @SerializedName("origin") val origin: String,
    @SerializedName("rare") val rare: Int,
    @SerializedName("rex") val rex: Int,
    @SerializedName("shedding_level") val shedding_level: Int,
    @SerializedName("short_legs") val short_legs: Int,
    @SerializedName("social_needs") val social_needs: Int,
    @SerializedName("stranger_friendly") val stranger_friendly: Int,
    @SerializedName("suppressed_tail") val suppressed_tail: Int,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("vcahospitals_url") val vcahospitals_url: String,
    @SerializedName("vetstreet_url") val vetstreet_url: String,
    @SerializedName("vocalisation") val vocalisation: Int,
    @SerializedName("weight") val weight: Weight,
    @SerializedName("wikipedia_url") val wikipedia_url: String
//    @SerializedName("id") val imageID: String,
//    @SerializedName("url") val url: String
) : Serializable