package com.example.shareaboutcatsapp.ui.main.votes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB
import com.example.shareaboutcatsapp.data.local.room.db.votes.RoomVotes
import com.example.shareaboutcatsapp.data.model.votes.CreateVotes
import com.example.shareaboutcatsapp.data.model.votes.VotesModel
import com.example.shareaboutcatsapp.data.model.votes.VotesModelItem
import com.example.shareaboutcatsapp.data.repository.VotesRepo
import kotlinx.coroutines.launch

class VotesViewModel(private val votesRepo: VotesRepo, private val myRoomDB: MyRoomDB) :
    ViewModel() {
    var votes: MutableLiveData<VotesModel> = MutableLiveData()
    var notification: String = ""
    var createVotes: MutableLiveData<CreateVotes> = MutableLiveData()

    fun getVotes(xApiKey: String, limit: Int, page: Int) {
        viewModelScope.launch {
            val response = votesRepo.getYourVotes(xApiKey,limit, page)
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

    fun saveDataVotes(listVotes: VotesModel) {
        viewModelScope.launch {
            val listVotesAdd: ArrayList<RoomVotes> = ArrayList()
            listVotes.forEach {
                listVotesAdd.add(
                    RoomVotes(
                        it.id,
                        it.country_code,
                        it.created_at,
                        it.image_id,
                        it.sub_id,
                        it.value
                    )
                )
            }
            myRoomDB.getDAOBreeds.insertListVotes(listVotesAdd)
        }
    }

    fun getDataVotes() {
        viewModelScope.launch {
            val listVotesLocal = myRoomDB.getDAOBreeds.getVotes()
            val votesModel = VotesModel()
            listVotesLocal.forEach {
                votesModel.add(
                    VotesModelItem(
                        it.countryCodeVotes ?: "a",
                        it.createdAtBreeds ?: "",
                        it.idVotes,
                        it.imageIDVotes ?: "",
                        it.subIDVotes ?: "",
                        it.valueBreeds ?: 0
                    )
                )
            }
            votes.value = votesModel
        }
    }
}