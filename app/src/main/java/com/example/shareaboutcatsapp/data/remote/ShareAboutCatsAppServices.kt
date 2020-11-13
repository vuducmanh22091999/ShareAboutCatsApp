package com.example.shareaboutcatsapp.data.remote

import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ShareAboutCatsAppServices {
    @GET("categories")
    suspend fun getCategories(@Header("x-api-key") xApiKey: String): Response<CategoriesModel>

    @GET("favourites")
    suspend fun getFavourites(@Header("x-api-key") xApiKey: String): Response<FavouritesModel>

    @GET("breeds")
    suspend fun getBreeds(@Header("x-api-key") xApiKey: String): Response<BreedsModel>

    @GET("votes")
    suspend fun getVotes(@Header("x-api-key") xApiKey: String): Response<VotesModel>

    @GET("search")
    suspend fun getBreedsByName(
        @Header("x-api-key") xApiKey: String,
        @Query("q") q: String
    ): Response<BreedsModel>
}