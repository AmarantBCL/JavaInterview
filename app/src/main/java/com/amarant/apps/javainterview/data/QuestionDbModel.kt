package com.amarant.apps.javainterview.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val categoryId: Int,
    val header: String,
    val description: String,
    val answer: String,
    var isAnswered: Boolean,
    var progressColor: Int
)
