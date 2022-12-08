package com.example.teststrarwars.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.teststrarwars.REALISATION
import com.example.teststrarwars.models.PeopleItem

class FavoriteFragmentViewModel: ViewModel() {

    fun getAllPeople(): LiveData<List<PeopleItem>> {
        return REALISATION.allPeople
    }
}