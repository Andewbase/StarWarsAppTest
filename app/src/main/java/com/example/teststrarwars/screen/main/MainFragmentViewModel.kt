package com.example.teststrarwars.screen.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.teststrarwars.REALISATION
import com.example.teststrarwars.screen.data.retrofit.RetrofitRepository
import com.example.teststrarwars.screen.data.room.PeopleRoomDatabase
import com.example.teststrarwars.screen.data.room.repository.PeopleRepositoryRealization
import com.example.teststrarwars.models.Peoples
import kotlinx.coroutines.launch
import retrofit2.Response

class MainFragmentViewModel(application: Application): AndroidViewModel(application) {

    private val repository = RetrofitRepository()

    private val _myPeople: MutableLiveData<Response<Peoples>> = MutableLiveData()
    val myPeople: LiveData<Response<Peoples>> = _myPeople

    val context = application

    fun getPeopleRetrofit(){
        viewModelScope.launch {
            try {
                _myPeople.value = repository.getPeoples()
            }catch (e:Exception){
                Log.e("ERROR", e.message.toString())
            }
        }
    }

    fun initDatabase(){//База данных инициализирована, но пустая и не создана
        val daoPeople = PeopleRoomDatabase.getInstance(context).getPeopleDao()
        REALISATION = PeopleRepositoryRealization(daoPeople)
    }


}