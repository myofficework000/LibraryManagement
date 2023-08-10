package com.example.libreasy.database.entity

data class Book(
    val id: Long=0L,
    val title: String,
    val author: String,
    val genre: String,
    val rating: Int,
    val year: String,
    val description: String = "",
)

