package com.example.shareaboutcatsapp.data.repository

import com.example.shareaboutcatsapp.data.remote.ShareAboutCatsAppServices

class VotesRepo(private val shareAboutCatsAppServices: ShareAboutCatsAppServices) {
    suspend fun getYourVotes(xApiKey: String) = shareAboutCatsAppServices.getVotes(xApiKey)
}