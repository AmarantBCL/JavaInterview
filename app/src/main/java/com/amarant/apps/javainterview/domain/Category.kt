package com.amarant.apps.javainterview.domain

data class Category(
    val id: Int,
    val title: String,
    var numberOfQuestions: Int = 0,
    var maxQuestions: Int = 0
)
