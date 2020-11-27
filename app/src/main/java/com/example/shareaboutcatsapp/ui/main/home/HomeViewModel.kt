package com.example.shareaboutcatsapp.ui.main.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shareaboutcatsapp.data.local.room.db.MyRoomDB
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories
import com.example.shareaboutcatsapp.data.local.room.db.favourites.RoomFavourites
import com.example.shareaboutcatsapp.data.local.room.db.image.RoomImage
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModel
import com.example.shareaboutcatsapp.data.model.breeds.BreedsModelItem
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModel
import com.example.shareaboutcatsapp.data.model.categories.CategoriesModelItem
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModel
import com.example.shareaboutcatsapp.data.model.favourites.FavouritesModelItem
import com.example.shareaboutcatsapp.data.model.image.ImageModel
import com.example.shareaboutcatsapp.data.model.image.ImageModelItem
import com.example.shareaboutcatsapp.data.repository.BreedsRepo
import com.example.shareaboutcatsapp.data.repository.CategoriesRepo
import com.example.shareaboutcatsapp.data.repository.FavouritesRepo
import kotlinx.coroutines.launch

class HomeViewModel(
    private val categoriesRepo: CategoriesRepo,
    private val favouritesRepo: FavouritesRepo,
    private val breedsRepo: BreedsRepo,
    private val myRoomDB: MyRoomDB
) : ViewModel() {
    var categories: MutableLiveData<CategoriesModel> = MutableLiveData()
    var favourites: MutableLiveData<FavouritesModel> = MutableLiveData()
    var breeds: MutableLiveData<BreedsModel> = MutableLiveData()
    var image: MutableLiveData<ImageModel> = MutableLiveData()

    fun getCategories(xApiKey: String) {
        viewModelScope.launch {
            val response = categoriesRepo.getAllCategories(xApiKey)
            if (response.isSuccessful && response.body() != null) {
                categories.value = response.body()
            }
        }
    }

    fun getFavourites(xApiKey: String, limit: Int, page: Int) {
        viewModelScope.launch {
            val response = favouritesRepo.getAllFavourites(xApiKey, limit, page)
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

    fun getImage(xApiKey: String, categoryID: Int, limit: Int, page: Int) {
        viewModelScope.launch {
            val response = categoriesRepo.getAllImage(xApiKey, categoryID, limit, page)
            if (response.isSuccessful && response.body() != null) {
                image.value = response.body()
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

//    fun getImageBreedsByBreedsID(xApiKey: String, breedsID: String, limit: Int, page: Int) {
//        viewModelScope.launch {
//            val response = breedsRepo.getImageBreedsByBreedsID(xApiKey, breedsID, limit, page)
//            if (response.isSuccessful && response.body() != null) {
//                breeds.value = response.body()
//            }
//        }
//    }

    fun saveDataCategories(listCategories: CategoriesModel) {
        viewModelScope.launch {
            val listCategoriesAdd: ArrayList<RoomCategories> = ArrayList()
            listCategories.forEach {
                listCategoriesAdd.add(RoomCategories(it.id, it.name))
            }
            myRoomDB.getDAOBreeds.insertListCategories(listCategoriesAdd)
        }
    }

    fun getDataCategories() {
        viewModelScope.launch {
            val listCategoriesLocal = myRoomDB.getDAOBreeds.getCategories()
            val categoriesModel = CategoriesModel()
            listCategoriesLocal.forEach {
                categoriesModel.add(CategoriesModelItem(it.idCategories, it.nameCategories))
            }
            categories.value = categoriesModel
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

    fun saveDetailsCategories(listImage: ImageModel) {
        viewModelScope.launch {
            val listImageAdd: ArrayList<RoomImage> = ArrayList()
            listImage.forEach {
                listImageAdd.add(
                    RoomImage(
                        it.categoriesModelItem,
                        it.url
                    )
                )
            }
            myRoomDB.getDAOBreeds.insertListImage(listImageAdd)
        }
    }

    fun getDataDetailsCategories() {
        viewModelScope.launch {
            val listImageLocal = myRoomDB.getDAOBreeds.getImageCategories()
            val imageModel = ImageModel()
            listImageLocal.forEach {
                it.categoriesModelItem?.let { it1 ->
                    ImageModelItem(
                        it1,
                        it.urlImage ?: ""
                    )
                }?.let { it2 ->
                    imageModel.add(
                        it2
                    )
                }
            }
            image.value = imageModel
        }
    }

    fun saveDataBreeds(listBreeds: BreedsModel) {
        viewModelScope.launch {
            val listBreedsAdd: ArrayList<RoomBreeds> = ArrayList()
            listBreeds.forEach {
                listBreedsAdd.add(
                    RoomBreeds(
                        it.adaptability,
                        it.affection_level,
                        it.alt_names,
                        it.bidability,
                        it.cat_friendly,
                        it.cfa_url,
                        it.child_friendly,
                        it.country_code,
                        it.country_codes,
                        it.description,
                        it.dog_friendly,
                        it.energy_level,
                        it.experimental,
                        it.grooming,
                        it.hairless,
                        it.health_issues,
                        it.hypoallergenic,
                        it.id,
                        it.indoor,
                        it.intelligence,
                        it.lap,
                        it.life_span,
                        it.name,
                        it.natural,
                        it.origin,
                        it.rare,
                        it.rex,
                        it.shedding_level,
                        it.short_legs,
                        it.stranger_friendly,
                        it.suppressed_tail,
                        it.suppressed_tail,
                        it.temperament,
                        it.vcahospitals_url,
                        it.vetstreet_url,
                        it.vocalisation,
                        it.weight,
                        it.wikipedia_url
//                        it.imageID,
//                        it.url
                    )
                )
            }
            myRoomDB.getDAOBreeds.insertListBreeds(listBreedsAdd)
        }
    }

    fun getDataBreeds() {
        viewModelScope.launch {
            val listBreedsLocal = myRoomDB.getDAOBreeds.getBreeds()
            val breedsModel = BreedsModel()
            listBreedsLocal.forEach {
                breedsModel.add(
                    BreedsModelItem(
                        it.adaptabilityBreeds ?: 0,
                        it.affectionLevelBreeds ?: 0,
                        it.altNamesBreeds ?: "",
                        it.bidabilityBreeds ?: 0,
                        it.catFriendlyBreeds ?: 0,
                        it.cfaUrlBreeds ?: "",
                        it.childFriendlyBreeds ?: 0,
                        it.countryCodeBreeds ?: "",
                        it.countryCodesBreeds ?: "",
                        it.descriptionBreeds ?: "",
                        it.dogFriendlyBreeds ?: 0,
                        it.energyLevelBreeds ?: 0,
                        it.experimentalBreeds ?: 0,
                        it.groomingBreeds ?: 0,
                        it.hairlessBreeds ?: 0,
                        it.healthIssuesBreeds ?: 0,
                        it.hypoallergenicBreeds ?: 0,
                        it.idBreeds,
                        it.indoorBreeds ?: 0,
                        it.intelligenceBreeds ?: 0,
                        it.lapBreeds ?: 0,
                        it.lifeSpanBreeds ?: "",
                        it.nameBreeds ?: "",
                        it.naturalBreeds ?: 0,
                        it.originBreeds ?: "",
                        it.rareBreeds ?: 0,
                        it.rexBreeds ?: 0,
                        it.sheddingLevelBreeds ?: 0,
                        it.shortLegsBreeds ?: 0,
                        it.socialNeedsBreeds ?: 0,
                        it.strangerFriendlyBreeds ?: 0,
                        it.suppressedTailBreeds ?: 0,
                        it.temperamentBreeds ?: "",
                        it.vcahospitalsUrlBreeds ?: "",
                        it.vetstreetUrlBreeds ?: "",
                        it.vocalisationBreeds ?: 0,
                        it.weightBreeds,
                        it.wikipediaUrlBreeds ?: ""
//                        it.idImage ?: "",
//                        it.url ?: ""
                    )
                )
            }
            breeds.value = breedsModel
        }
    }
}