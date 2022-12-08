package com.example.teststrarwars.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststrarwars.REALISATION
import com.example.teststrarwars.models.PeopleItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsFragmentViewModels: ViewModel() {

    fun insert(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REALISATION.insertPeople(peopleItem){
                onSuccess()
            }
        }
    fun delete(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO){
            REALISATION.deletePeople(peopleItem){
                onSuccess()
            }
        }
}