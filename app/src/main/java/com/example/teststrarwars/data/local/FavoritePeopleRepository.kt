package com.example.teststrarwars.data.local

import javax.inject.Inject

class FavoritePeopleRepository @Inject constructor(
    private val favoritePeopleDao: FavoritePeopleDao
) {

    suspend fun addFavorite(favoritePeople: FavoritePeople) = favoritePeopleDao.addFavorite(favoritePeople)

    fun getFavoritePeoples() = favoritePeopleDao.getFavoritePeoples()

    suspend fun checkPeople(id: Int) = favoritePeopleDao.checkPeople(id)

    suspend fun removeFromPeople(id: Int) {
        favoritePeopleDao.removeFromFavorite(id)
    }
}