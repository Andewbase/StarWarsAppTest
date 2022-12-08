package com.example.teststrarwars.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "people_table")
data class PeopleItem(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val name: String,
    val isFavorite: Boolean
): Serializable