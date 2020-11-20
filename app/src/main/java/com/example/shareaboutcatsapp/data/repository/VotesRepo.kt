package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class VotesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getYourVotes(xApiKey: String) = shareAboutCatsAppServices.getVotes(xApiKey)

    suspend fun createVotes(xApiKey: String, image_id: String, sub_id: String, value: String) =
        shareAboutCatsAppServices.createVotes(xApiKey, image_id, sub_id, value)

    suspend fun deleteVotes(xApiKey: String, voteID: Int) =
        shareAboutCatsAppServices.deleteVotes(xApiKey, voteID)
}