package com.amarant.apps.javainterview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface AppDao {

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<CategoryDbModel>

    @Query("SELECT * FROM questions WHERE categoryId = :id")
    fun getQuestionsByCategory(id: Int): LiveData<QuestionDbModel>
}