package com.example.teststrarwars.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststrarwars.data.retrofit.PeopleRepository
import com.example.teststrarwars.models.PeopleItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsFragmentViewModels @Inject constructor(private val repository: PeopleRepository) : ViewModel() {

    fun insert(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            repository.insertPeopleDao(peopleItem){
                onSuccess()
            }
        }

    fun delete(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            repository.deletePeopleDao(peopleItem){
                onSuccess()
            }
        }
}