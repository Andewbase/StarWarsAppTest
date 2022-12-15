package com.example.teststrarwars.data.retrofit

import androidx.lifecycle.LiveData
import com.example.teststrarwars.data.retrofit.api.ApiService
import com.example.teststrarwars.data.room.dao.PeopleDao
import com.example.teststrarwars.models.PeopleItem
import javax.inject.Inject

class PeopleRepository @Inject constructor(
    private val peopleService: ApiService,
    private val peopleDao: PeopleDao
) {

    suspend fun getPeoples(pageNumber: Int) =
        peopleService.getPeoples(page = pageNumber)

    fun getAllPeopleDao(): LiveData<List<PeopleItem>> = peopleDao.getAllPeople()

    suspend fun insertPeopleDao(peopleItem: PeopleItem, onSuccess:() -> Unit) {
        peopleDao.insert(peopleItem)
        onSuccess()
    }

    suspend fun deletePeopleDao(peopleItem: PeopleItem, onSuccess: () -> Unit){
        peopleDao.insert(peopleItem)
    }

 //   suspend fun setIsFavorite(peopleItem: PeopleItem, isFavorite: Boolean){}
}