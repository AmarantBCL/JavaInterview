package com.amarant.apps.javainterview.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amarant.apps.javainterview.data.AppRepositoryImpl
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.GetAllCategoriesUseCase
import com.amarant.apps.javainterview.domain.GetQuestionsByCategoryUseCase
import com.amarant.apps.javainterview.domain.Question

class MainViewModel(private val application: Application) : ViewModel() {

    private val repository = AppRepositoryImpl(application)

    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(repository)
    private val getQuestionsByCategoryUseCase = GetQuestionsByCategoryUseCase(repository)

    fun getAllCategories(): LiveData<List<Category>> {
        return getAllCategoriesUseCase()
    }

    fun getQuestionsByCategory(id: Int): LiveData<List<Question>> {
        return getQuestionsByCategoryUseCase(id)
    }
}