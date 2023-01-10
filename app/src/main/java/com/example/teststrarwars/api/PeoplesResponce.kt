package com.example.teststrarwars.api

import com.example.teststrarwars.data.models.People

data class PeoplesResponce(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<People>
)