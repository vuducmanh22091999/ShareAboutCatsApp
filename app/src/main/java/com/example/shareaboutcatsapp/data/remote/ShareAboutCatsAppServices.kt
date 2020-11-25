package com.example.shareaboutcatsapp.data.remote

import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.data.model.votes.CreateVotes
import com.example.shareaboutcatsapp.data.model.votes.NotificationDelete
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import retrofit2.Response
import retrofit2.http.*

interface ShareAboutCatsAppServices {
    @GET("categories")
    suspend fun getCategories(@Header("x-api-key") xApiKey: String): Response<CategoriesModel>

    @GET("favourites")
    suspend fun getFavourites(
        @Header("x-api-key") xApiKey: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<FavouritesModel>

    @GET("breeds")
    suspend fun getBreeds(@Header("x-api-key") xApiKey: String): Response<BreedsModel>

    @GET("votes")
    suspend fun getVotes(
        @Header("x-api-key") xApiKey: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<VotesModel>

    @GET("search")
    suspend fun getBreedsByName(
        @Header("x-api-key") xApiKey: String,
        @Query("q") q: String
    ): Response<BreedsModel>

    @DELETE("votes/{vote_id}")
    suspend fun deleteVotes(
        @Header("x-api-key") xApiKey: String,
        @Path("vote_id") voteID: Int
    ): Response<NotificationDelete>

    @POST("votes")
    suspend fun createVotes(
        @Header("x-api-key") xApiKey: String,
        @Body image_id: String,
        @Body sub_id: String,
        @Body value: String
    ): Response<CreateVotes>

    @DELETE("favourites/{favourite_id}")
    suspend fun deleteFavourites(
        @Header("x-api-key") xApiKey: String,
        @Path("favourite_id") favouritesID: Int
    ): Response<NotificationDelete>

    @GET("images/search")
    suspend fun getImageByCategoriesID(
        @Header("x-api-key") xApiKey: String,
        @Query("category_ids") categoryID: Int,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Response<ImageModel>
}