package com.example.teststrarwars.screen.data.room.repository

import androidx.lifecycle.LiveData
import com.example.teststrarwars.screen.data.room.dao.PeopleDao
import com.example.teststrarwars.models.PeopleItem

class PeopleRepositoryRealization(private val peopleDao: PeopleDao): PeoplesRepository {

    override val allPeople: LiveData<List<PeopleItem>>
        get() = peopleDao.getAllPeople()

    override suspend fun insertPeople(peopleItemModel: PeopleItem, onSuccess: () -> Unit) {
        peopleDao.insert((peopleItemModel))
        onSuccess()
    }

    override suspend fun deletePeople(peopleItemModel: PeopleItem, onSuccess: () -> Unit) {
        peopleDao.delete(peopleItemModel)
        onSuccess()
    }
}