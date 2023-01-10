package com.example.teststrarwars.screen.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.teststrarwars.data.local.FavoritePeopleRepository
import com.example.teststrarwars.data.models.People
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteFragmentViewModel @Inject constructor(private val repository: FavoritePeopleRepository) : ViewModel() {

  val people = repository.getFavoritePeoples()
}