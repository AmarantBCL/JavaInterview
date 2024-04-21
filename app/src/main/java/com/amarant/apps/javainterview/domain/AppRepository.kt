package com.amarant.apps.javainterview.domain

interface AppRepository {

    fun getAllCategories(): List<Category>

    fun getQuestionByCategory(id: Int): List<Question>
}