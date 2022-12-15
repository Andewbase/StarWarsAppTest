package com.example.teststrarwars.screen.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.teststrarwars.data.retrofit.PeopleRepository
import com.example.teststrarwars.models.PeopleItem
import com.example.teststrarwars.models.Peoples
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor (private val repository: PeopleRepository): ViewModel() {

    private val _myPeople: MutableLiveData<Peoples> = MutableLiveData()
    val myPeople: LiveData<Peoples> = _myPeople

   //val isFavoriteItem: LiveData<Boolean> = Transformations.map(myPeople) {}

    private val newPage = 1

    fun getPeopleRetrofit() =
        viewModelScope.launch {
            repository.getPeoples(pageNumber = newPage).let { response ->
                if (response.isSuccessful){
                    _myPeople.postValue(response.body())
                }else{
                    Log.d("response", "error: ${response.code()}")
                }
            }
        }

    fun insertDAO(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch (Dispatchers.IO) {
            repository.insertPeopleDao(peopleItem){
                onSuccess()
            }
        }

    fun deleteDAO(peopleItem: PeopleItem, onSuccess:() -> Unit) =
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPeopleDao(peopleItem){
                onSuccess
            }
        }


}