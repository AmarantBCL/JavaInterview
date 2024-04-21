package com.amarant.apps.javainterview.domain

import androidx.lifecycle.LiveData

class GetQuestionsByCategoryUseCase(private val repository: AppRepository) {

    operator fun invoke(id: Int): LiveData<Question> {
        return repository.getQuestionByCategory(id)
    }
}