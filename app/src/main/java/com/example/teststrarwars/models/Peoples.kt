package com.example.teststrarwars.models

data class Peoples(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PeopleItem>
)