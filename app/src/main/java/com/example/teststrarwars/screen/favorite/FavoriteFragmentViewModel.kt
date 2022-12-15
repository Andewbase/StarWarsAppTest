package com.example.teststrarwars.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.teststrarwars.data.retrofit.PeopleRepository
import com.example.teststrarwars.models.PeopleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel @Inject constructor(private val repository: PeopleRepository) : ViewModel() {

    fun getAllPeople(): LiveData<List<PeopleItem>> {
        return repository.getAllPeopleDao()
    }
}