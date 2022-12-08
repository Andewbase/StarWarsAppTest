package com.example.teststrarwars.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teststrarwars.data.room.dao.PeopleDao
import com.example.teststrarwars.models.PeopleItem

@Database(entities = [PeopleItem::class], version = 2)
abstract class PeopleRoomDatabase: RoomDatabase() {

    abstract fun getPeopleDao(): PeopleDao

    companion object{
        private var database: PeopleRoomDatabase?= null

        fun getInstance(context: Context): PeopleRoomDatabase {
            return if (database == null){
                database = Room
                    .databaseBuilder(context, PeopleRoomDatabase::class.java, "db")
                    .build()
                database as PeopleRoomDatabase
            }else{
                database as PeopleRoomDatabase
            }
        }
    }
}