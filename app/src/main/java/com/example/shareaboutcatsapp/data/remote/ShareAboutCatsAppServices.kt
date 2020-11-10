package com.example.shareaboutcatsapp.data.remote

import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ShareAboutCatsAppServices {
    @GET("categories")
    suspend fun getCategories(@Header("x-api-key") xApiKey: String): Response<CategoriesModel>

    @GET("favourites")
    suspend fun getFavourites(@Header("x-api-key") xApiKey: String): Response<FavouritesModel>

    @GET("breeds")
    suspend fun getBreeds(@Header("x-api-key") xApiKey: String): Response<BreedsModel>
}