package com.example.shareaboutcatsapp.data.local.room.db.breeds

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shareaboutcatsapp.data.model.breeds.Weight

@Entity(tableName = "breeds")
data class RoomBreeds(
    val adaptabilityBreeds: Int? = null,
    val affectionLevelBreeds: Int? = null,
    val altNamesBreeds: String? = null,
    val bidabilityBreeds: Int? = null,
    val catFriendlyBreeds: Int? = null,
    val cfaUrlBreeds: String? = null,
    val childFriendlyBreeds: Int? = null,
    val countryCodeBreeds: String? = null,
    val countryCodesBreeds: String? = null,
    val descriptionBreeds: String? = null,
    val dogFriendlyBreeds: Int? = null,
    val energyLevelBreeds: Int? = null,
    val experimentalBreeds: Int? = null,
    val groomingBreeds: Int? = null,
    val hairlessBreeds: Int? = null,
    val healthIssuesBreeds: Int? = null,
    val hypoallergenicBreeds: Int? = null,
    @PrimaryKey
    val idBreeds: String,
    val indoorBreeds: Int? = null,
    val intelligenceBreeds: Int? = null,
    val lapBreeds: Int? = null,
    val lifeSpanBreeds: String? = null,
    val nameBreeds: String? = null,
    val naturalBreeds: Int? = null,
    val originBreeds: String? = null,
    val rareBreeds: Int? = null,
    val rexBreeds: Int? = null,
    val sheddingLevelBreeds: Int? = null,
    val shortLegsBreeds: Int? = null,
    val socialNeedsBreeds: Int? = null,
    val strangerFriendlyBreeds: Int? = null,
    val suppressedTailBreeds: Int? = null,
    val temperamentBreeds: String? = null,
    val vcahospitalsUrlBreeds: String? = null,
    val vetstreetUrlBreeds: String? = null,
    val vocalisationBreeds: Int? = null,
    @Embedded val weightBreeds: Weight,
    val wikipediaUrlBreeds: String? = null
)