package com.amarant.apps.javainterview.domain

import androidx.lifecycle.LiveData

interface AppRepository {

    fun getAllCategories(): LiveData<List<Category>>

    fun getQuestionByCategory(id: Int): LiveData<List<Question>>
}