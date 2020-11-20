package com.example.shareaboutcatsapp.ui.main.votes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.model.votes.CreateVotes
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import com.example.shareaboutcatsapp.data.repository.VotesRepo
import kotlinx.coroutines.launch

class VotesViewModel(private val votesRepo: VotesRepo) : ViewModel() {
    var votes: MutableLiveData<VotesModel> = MutableLiveData()
    var notification: String = ""
    var createVotes: MutableLiveData<CreateVotes> = MutableLiveData()

    fun getVotes(xApiKey: String) {
        viewModelScope.launch {
            val response = votesRepo.getYourVotes(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                votes.value = response.body()
            }
        }
    }

    fun createVotes(xApiKey: String, image_id: String, sub_id: String, value: String) {
        viewModelScope.launch {
            val response = votesRepo.createVotes(xApiKey, image_id, sub_id, value)
            if (response.isSuccessful && response.body() != null) {
                createVotes.value = response.body()
            }
        }
    }

    fun deleteVotes(xApiKey: String, voteID: Int) {
        viewModelScope.launch {
            val response = votesRepo.deleteVotes(xApiKey, voteID)
            if (response.isSuccessful && response.body() != null) {
                notification = "Success"
            }
        }
    }
}