package com.amarant.apps.javainterview.domain

data class Question(
    val id: Int,
    val categoryId: Int,
    val header: String,
    val description: String,
    val answer: String,
    var isAnswered: Boolean
)
