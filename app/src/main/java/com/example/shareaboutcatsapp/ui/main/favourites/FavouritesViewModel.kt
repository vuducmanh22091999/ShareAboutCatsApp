package com.example.shareaboutcatsapp.ui.main.favourites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.repository.FavouritesRepo
import kotlinx.coroutines.launch

class FavouritesViewModel(private val favouritesRepo: FavouritesRepo) : ViewModel(){
    var favourites: MutableLiveData<FavouritesModel> = MutableLiveData()

    fun getFavourites(xApiKey: String) {
        viewModelScope.launch {
            val response = favouritesRepo.getAllFavourites(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                favourites.value = response.body()
            }
        }
    }
}