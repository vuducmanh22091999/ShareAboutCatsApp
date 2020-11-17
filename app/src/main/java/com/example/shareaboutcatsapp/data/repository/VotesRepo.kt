package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class VotesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getYourVotes(xApiKey: String) = shareAboutCatsAppServices.getVotes(xApiKey)

    suspend fun createVotes(xApiKey: String) = shareAboutCatsAppServices.createVotes(xApiKey)

    suspend fun deleteVotes(xApiKey: String, voteID: Int) =
        shareAboutCatsAppServices.deleteVotes(xApiKey, voteID)
}