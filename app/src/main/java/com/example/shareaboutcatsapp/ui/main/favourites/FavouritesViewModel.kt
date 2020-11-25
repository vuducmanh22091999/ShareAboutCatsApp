package com.example.shareaboutcatsapp.ui.main.favourites

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB
import com.example.shareaboutcatsapp.data.local.room.db.favourites.RoomFavourites
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.data.repository.FavouritesRepo
import kotlinx.coroutines.launch

class FavouritesViewModel(
    private val favouritesRepo: FavouritesRepo,
    private val myRoomDB: MyRoomDB
) : ViewModel() {
    var favourites: MutableLiveData<FavouritesModel> = MutableLiveData()
    var notification: String = ""

    fun getFavourites(xApiKey: String, limit: Int, page: Int) {
        viewModelScope.launch {
            val response = favouritesRepo.getAllFavourites(xApiKey, limit, page)
            if (response.isSuccessful && response.body() != null) {
                favourites.value = response.body()
            }
        }
    }

    fun deleteFavourites(xApiKey: String, favouritesID: Int) {
        viewModelScope.launch {
            val response = favouritesRepo.deleteFavourites(xApiKey, favouritesID)
            if (response.isSuccessful) {
                notification = response.message()
            }
        }
    }

    fun saveDataFavourites(listFavourites: FavouritesModel) {
        viewModelScope.launch {
            val listFavouritesAdd: ArrayList<RoomFavourites> = ArrayList()
            listFavourites.forEach {
                listFavouritesAdd.add(
                    RoomFavourites(
                        it.id,
                        it.sub_id,
                        it.user_id,
                        it.created_at,
                        it.image_id,
                        it.image
                    )
                )
            }
            myRoomDB.getDAOBreeds.insertListFavourites(listFavouritesAdd)
        }
    }

    fun getDataFavourites() {
        viewModelScope.launch {
            val listFavouritesLocal = myRoomDB.getDAOBreeds.getFavourites()
            val favouritesModel = FavouritesModel()
            listFavouritesLocal.forEach {
                favouritesModel.add(
                    FavouritesModelItem(
                        it.createAtFavourites,
                        it.idFavourites,
                        it.imageFavourites!!,
                        it.imageIDFavourites,
                        it.subIDFavourites,
                        it.userIDFavourites
                    )
                )
            }
            favourites.value = favouritesModel
        }
    }
}