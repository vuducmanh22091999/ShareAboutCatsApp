package com.example.shareaboutcatsapp.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shareaboutcatsapp.data.local.room.db.breeds.RoomBreeds
import com.example.shareaboutcatsapp.data.local.room.db.categories.RoomCategories
import com.example.shareaboutcatsapp.data.local.room.db.favourites.RoomFavourites
import com.example.shareaboutcatsapp.data.local.room.db.image.RoomImage
import com.example.shareaboutcatsapp.data.local.room.db.votes.RoomVotes

@Dao
interface DAOBreeds {
    @Insert
    fun insertBreeds(roomBreeds: RoomBreeds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListBreeds(listBreeds: List<RoomBreeds>)

    @Query("Select nameBreeds From breeds")
    fun getNameBreeds(): MutableList<String>

    @Query("Select * From breeds")
    fun getBreeds(): MutableList<RoomBreeds>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListCategories(listRoomCategories: List<RoomCategories>)

    @Insert
    fun insertCategories(roomCategories: RoomCategories)

    @Query("Select * From categories")
    fun getCategories(): MutableList<RoomCategories>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListFavourites(listRoomFavourites: List<RoomFavourites>)

    @Insert
    fun insertFavourites(roomFavourites: RoomFavourites)

    @Query("Select * From favourites")
    fun getFavourites(): MutableList<RoomFavourites>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListVotes(listRoomVotes: List<RoomVotes>)

    @Insert
    fun insertVotes(roomVotes: RoomVotes)

    @Query("Select * From votes")
    fun getVotes(): MutableList<RoomVotes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertListImage(listRoomImage: List<RoomImage>)

    @Query("Select * From imageCategories")
    fun getImageCategories(): MutableList<RoomImage>
}