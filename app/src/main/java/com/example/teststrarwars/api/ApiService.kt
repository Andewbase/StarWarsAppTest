package com.example.teststrarwars.api

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    companion object{
        const val BASE_URL = "https://swapi.dev/api/"
    }

    @GET("people/")
    suspend fun getPeoples(
        @Query("page") position: Int
    ): PeoplesResponce

    @GET("people/")
    suspend fun searchPeople(
        @Query("page") query: Int,
        @Query("name") name: String
    ): PeoplesResponce
}