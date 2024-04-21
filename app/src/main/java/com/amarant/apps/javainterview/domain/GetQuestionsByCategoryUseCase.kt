package com.amarant.apps.javainterview.domain

class GetQuestionsByCategoryUseCase(private val repository: AppRepository) {

    operator fun invoke(id: Int): List<Question> {
        return repository.getQuestionByCategory(id)
    }
}