package com.example.teststrarwars.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teststrarwars.models.PeopleItem

@Dao
interface PeopleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)//Добавить  таблицу
    suspend fun insert(peopleItemModel: PeopleItem)

    @Delete
    suspend fun delete(peopleItemModel: PeopleItem)

    @Query("SELECT * from people_table") // Вернуть результат из таблицы
    fun getAllPeople(): LiveData<List<PeopleItem>>
}