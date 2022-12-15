package com.example.teststrarwars.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.teststrarwars.data.room.dao.PeopleDao
import com.example.teststrarwars.models.PeopleItem

@Database(entities = [PeopleItem::class], version = 1, exportSchema = true)
abstract class PeopleRoomDatabase: RoomDatabase() {
    abstract fun getPeopleDao(): PeopleDao
}