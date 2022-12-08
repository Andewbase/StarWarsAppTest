package com.example.teststrarwars.data.retrofit

import com.example.teststrarwars.data.retrofit.api.RetrofitInstance
import com.example.teststrarwars.models.Peoples
import retrofit2.Response

class RetrofitRepository {

    suspend fun getPeoples(): Response<Peoples> {
        return RetrofitInstance.api.getPeoples()
    }

}