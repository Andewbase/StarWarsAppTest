package com.example.teststrarwars.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.teststrarwars.api.ApiService
import com.example.teststrarwars.data.models.People
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.HttpException
import java.io.IOException


private const val STARTING_PAGE_INDEX = 1

class PeoplesPagingSource(
    private val apiService: ApiService,
    private val query: String?
): PagingSource<Int, People>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, People> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX

            val responce = if (query!=null) apiService.searchPeople(position, query) else apiService.getPeoples(position)

            val people = responce.results

            LoadResult.Page(
                data = people,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position -1,
                nextKey = position.plus(1)
            )
        }catch (e: IOException){
            LoadResult.Error(e)
        }catch (e: HttpException){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, People>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}