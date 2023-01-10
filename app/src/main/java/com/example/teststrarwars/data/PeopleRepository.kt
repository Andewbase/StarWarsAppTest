package com.example.teststrarwars.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.example.teststrarwars.api.ApiService
import javax.inject.Inject

class PeopleRepository @Inject constructor(private val apiService: ApiService) {


    fun getNowPlayingPeople() =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {PeoplesPagingSource(apiService, null)}
        ).liveData

    fun getSearchPeople(query: String) =
        Pager(
            config = PagingConfig(pageSize = 1, enablePlaceholders = false),
            pagingSourceFactory = {PeoplesPagingSource(apiService, query)}
        ).liveData
}