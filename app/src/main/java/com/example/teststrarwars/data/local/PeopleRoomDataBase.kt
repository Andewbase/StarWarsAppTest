package com.example.teststrarwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FavoritePeople::class], version = 1)

abstract class PeopleRoomDatabase: RoomDatabase() {
    abstract fun getFavoritePeopleDao(): FavoritePeopleDao
}