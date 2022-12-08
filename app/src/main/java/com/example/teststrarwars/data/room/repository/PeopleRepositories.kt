package com.example.teststrarwars.data.room.repository

import androidx.lifecycle.LiveData
import com.example.teststrarwars.models.PeopleItem

interface PeoplesRepository {
    val allPeople: LiveData<List<PeopleItem>>
    suspend fun insertPeople(movieItemModel: PeopleItem, onSuccess:() -> Unit)
    suspend fun deletePeople(movieItemModel: PeopleItem, onSuccess:() -> Unit)

}