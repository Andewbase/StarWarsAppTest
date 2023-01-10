package com.example.teststrarwars.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class People(
    val id: Int? = null,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val name: String
): Parcelable