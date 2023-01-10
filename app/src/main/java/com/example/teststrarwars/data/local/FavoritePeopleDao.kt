package com.example.teststrarwars.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritePeopleDao {

    @Insert
    suspend fun addFavorite(favoritePeople: FavoritePeople)

    @Query("SELECT * FROM favorite_people")
    fun getFavoritePeoples(): LiveData<List<FavoritePeople>>

    @Query("SELECT count(*) FROM favorite_people WHERE favorite_people.id = :id")
    suspend fun checkPeople(id: Int): Int

    @Query("DELETE FROM favorite_people WHERE favorite_people.id = :id")
    suspend fun removeFromFavorite(id: Int): Int
}