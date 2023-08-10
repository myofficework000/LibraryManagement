package com.example.libreasy.database.entity

data class Author (
    val id: Long=0L,
    val name: String,
    val rating: Int,
    val birth: String
)