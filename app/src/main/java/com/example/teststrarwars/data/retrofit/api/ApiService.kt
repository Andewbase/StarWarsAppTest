package com.example.teststrarwars.data.retrofit.api

import com.example.teststrarwars.models.Peoples
import com.example.teststrarwars.models.PeopleItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("people")
    suspend fun getPeoples(): Response<Peoples>

    @GET("people/{id}")
    suspend fun getNamePeople(@Path("id") id: Int): Response<PeopleItem>
}