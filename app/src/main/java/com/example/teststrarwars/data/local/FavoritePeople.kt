package com.example.teststrarwars.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.teststrarwars.data.models.People
import kotlinx.android.parcel.Parcelize
import java.io.Serializable


@Entity(tableName = "favorite_people")
@Parcelize
data class FavoritePeople(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val name: String
): Serializable, Parcelable{
    fun toPeople() = People(
        id = id,
        birth_year = birth_year,
        gender = gender,
        homeworld = homeworld,
        name = name
    )
}
