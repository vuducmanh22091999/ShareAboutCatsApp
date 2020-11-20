package com.example.shareaboutcatsapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.repository.BreedsRepo
import com.example.shareaboutcatsapp.data.repository.CategoriesRepo
import com.example.shareaboutcatsapp.data.repository.FavouritesRepo
import com.example.shareaboutcatsapp.data.repository.RoomRepo
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoriesRepo: CategoriesRepo,
    private val favouritesRepo: FavouritesRepo,
    private val breedsRepo: BreedsRepo,
) : ViewModel() {
    var categories: MutableLiveData<CategoriesModel> = MutableLiveData()
    var favourites: MutableLiveData<FavouritesModel> = MutableLiveData()
    var breeds: MutableLiveData<BreedsModel> = MutableLiveData()

    fun getCategories(xApiKey: String) {
        viewModelScope.launch {
            val response = categoriesRepo.getAllCategories(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                categories.value = response.body()
            }
        }
    }

    fun getFavourites(xApiKey: String) {
        viewModelScope.launch {
            val response = favouritesRepo.getAllFavourites(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                favourites.value = response.body()
            }
        }
    }

    fun getBreeds(xApiKey: String) {
        viewModelScope.launch {
            val response = breedsRepo.getAllBreeds(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                breeds.value = response.body()
            }
        }
    }

    fun getBreedsByName(xApiKey: String, q: String) {
        viewModelScope.launch {
            val response = breedsRepo.getBreedsByName(xApiKey, q)
            if (response.isSuccessful && response.body() != null) {
                breeds.value = response.body()
            }
        }
    }
}