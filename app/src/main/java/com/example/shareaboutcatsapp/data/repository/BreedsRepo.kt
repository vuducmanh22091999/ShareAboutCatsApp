package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class BreedsRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getAllBreeds(xApiKey: String) = shareAboutCatsAppServices.getBreeds(xApiKey)

    suspend fun getBreedsByName(xApiKey: String, q: String) = shareAboutCatsAppServices.getBreedsByName(xApiKey, q)
}