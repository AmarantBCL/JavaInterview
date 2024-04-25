package com.amarant.apps.javainterview.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.amarant.apps.javainterview.domain.Question

@Dao
interface AppDao {

    @Query("SELECT * FROM categories")
    fun getAllCategories(): LiveData<List<CategoryDbModel>>

    @Query("SELECT * FROM questions")
    fun getAllQuestions(): LiveData<List<QuestionDbModel>>

    @Query("SELECT * FROM questions WHERE categoryId = :id")
    fun getQuestionsByCategory(id: Int): LiveData<List<QuestionDbModel>>

    @Update
    fun setQuestionAnswered(question: QuestionDbModel)
}