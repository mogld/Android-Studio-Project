package com.example.proj1.kotlin

class Disease(
    val id: Long,
    val name: String,
    val description: String,
    val iconImage: String,
    val ageFilter: Int,
    val forbiddenHealthConditionFilter: Int,
)