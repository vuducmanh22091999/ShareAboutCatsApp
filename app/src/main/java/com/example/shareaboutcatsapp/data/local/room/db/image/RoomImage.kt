package com.example.shareaboutcatsapp.data.local.room.db.image

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem

@Entity(tableName = "imageCategories")
data class RoomImage(
    @PrimaryKey
    @Embedded val categoriesModelItem: ArrayList<CategoriesModelItem>? = null,
    val urlImage: String? = null
)
