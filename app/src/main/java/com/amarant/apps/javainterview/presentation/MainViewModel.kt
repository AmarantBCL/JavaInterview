package com.amarant.apps.javainterview.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarant.apps.javainterview.data.AppRepositoryImpl
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.GetAllCategoriesUseCase
import com.amarant.apps.javainterview.domain.GetQuestionsByCategoryUseCase
import com.amarant.apps.javainterview.domain.ProgressColor
import com.amarant.apps.javainterview.domain.Question
import com.amarant.apps.javainterview.domain.SetQuestionAnsweredUseCase
import com.amarant.apps.javainterview.domain.SwitchQuestionProgressUseCase
import kotlinx.coroutines.launch

class MainViewModel(private val application: Application) : ViewModel() {

    private val repository = AppRepositoryImpl(application)

    private val getAllCategoriesUseCase = GetAllCategoriesUseCase(repository)
    private val getQuestionsByCategoryUseCase = GetQuestionsByCategoryUseCase(repository)
    private val setQuestionAnsweredUseCase = SetQuestionAnsweredUseCase(repository)
    private val switchQuestionProgressUseCase = SwitchQuestionProgressUseCase(repository)

    fun getAllCategories(): LiveData<List<Category>> {
        return getAllCategoriesUseCase()
    }

    fun getQuestionsByCategory(id: Int): LiveData<List<Question>> {
        return getQuestionsByCategoryUseCase(id)
    }

    fun setQuestionAnswered(question: Question) {
        viewModelScope.launch {
            setQuestionAnsweredUseCase(question)
        }
    }

    fun switchQuestionProgress(question: Question) {
        val progress = when(question.progressColor) {
            ProgressColor.DIFFICULT -> ProgressColor.MEDIUM
            ProgressColor.MEDIUM -> ProgressColor.EASY
            ProgressColor.EASY -> ProgressColor.DIFFICULT
        }
        viewModelScope.launch {
            switchQuestionProgressUseCase(question.copy(
                progressColor = progress
            ))
        }
    }
}