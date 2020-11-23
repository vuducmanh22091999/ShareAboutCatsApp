package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class CategoriesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getAllCategories(xApiKey: String) = shareAboutCatsAppServices.getCategories(xApiKey)
}