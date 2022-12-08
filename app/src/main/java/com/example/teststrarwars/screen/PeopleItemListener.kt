package com.example.teststrarwars.screen

import com.example.teststrarwars.models.PeopleItem

interface PeopleItemListener {

    fun peopleGo(peopleItem: PeopleItem)

    fun peopleIsFavorite(peopleItem: PeopleItem)

}