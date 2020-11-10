package com.example.shareaboutcatsapp.ui.main.votes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import com.example.shareaboutcatsapp.data.repository.VotesRepo
import kotlinx.coroutines.launch

class VotesViewModel(private val votesRepo: VotesRepo) : ViewModel() {
    var votes: MutableLiveData<VotesModel> = MutableLiveData()

    fun getVotes(xApiKey: String) {
        viewModelScope.launch {
            val response = votesRepo.getYourVotes(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                votes.value = response.body()
            }
        }
    }
}