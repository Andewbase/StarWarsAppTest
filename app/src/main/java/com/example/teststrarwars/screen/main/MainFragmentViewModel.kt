package com.example.teststrarwars.screen.main

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.teststrarwars.data.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor (
    private val repository: PeopleRepository,
    private val state: SavedStateHandle
): ViewModel() {

    companion object{
        private const val CURRENT_QUERY = "current_query"
        private const val EMPTY_QUERY = ""
    }

    private val currentQuery = state.getLiveData(CURRENT_QUERY, EMPTY_QUERY)

    val people = currentQuery.switchMap { query ->
        if (query.isNotEmpty()){
            repository.getSearchPeople(query).cachedIn(viewModelScope)
        }else{
            repository.getNowPlayingPeople().cachedIn(viewModelScope)
        }
    }

    fun searchPeople(query: String){
        currentQuery.value = query
    }

}