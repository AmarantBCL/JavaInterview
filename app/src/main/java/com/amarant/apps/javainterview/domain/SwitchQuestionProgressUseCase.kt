package com.amarant.apps.javainterview.domain

class SwitchQuestionProgressUseCase(private val repository: AppRepository) {

    suspend operator fun invoke(question: Question) {
        return repository.setQuestionAnswered(question)
    }
}