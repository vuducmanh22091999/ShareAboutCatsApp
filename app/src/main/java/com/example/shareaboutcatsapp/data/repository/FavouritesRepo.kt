package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class FavouritesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getAllFavourites(xApiKey: String, limit: Int, page: Int) =
        shareAboutCatsAppServices.getFavourites(xApiKey, limit, page)

    suspend fun deleteFavourites(xApiKey: String, favouritesID: Int) =
        shareAboutCatsAppServices.deleteFavourites(xApiKey, favouritesID)
}