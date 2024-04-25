package com.amarant.apps.javainterview.domain

class SetQuestionAnsweredUseCase(private val repository: AppRepository) {

    suspend operator fun invoke(question: Question) {
        return repository.setQuestionAnswered(question)
    }
}