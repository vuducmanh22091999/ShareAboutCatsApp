package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class CategoriesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getAllCategories(xApiKey: String) = shareAboutCatsAppServices.getCategories(xApiKey)

    suspend fun getAllImage(xApiKey: String, categoryID: Int, limit: Int, page: Int) =
        shareAboutCatsAppServices.getImageByCategoriesID(xApiKey, categoryID, limit, page)
}