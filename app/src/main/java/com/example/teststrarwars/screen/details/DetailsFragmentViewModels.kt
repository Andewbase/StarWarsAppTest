package com.example.teststrarwars.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststrarwars.data.local.FavoritePeople
import com.example.teststrarwars.data.local.FavoritePeopleRepository
import com.example.teststrarwars.data.models.People
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModels @Inject constructor(
    private val repository: FavoritePeopleRepository
) : ViewModel() {


    fun addToFavorite(people: People){
        CoroutineScope(Dispatchers.IO).launch {
            repository.addFavorite(
                FavoritePeople(
                    people.id!!,
                    people.birth_year,
                    people.gender,
                    people.homeworld,
                    people.name
                )
            )
        }
    }

    suspend fun checkPeople(id: Int) = repository.checkPeople(id)

    fun removeFavoritePeople(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeFromPeople(id)
        }
    }
}