package com.amarant.apps.javainterview.domain

class GetAllCategoriesUseCase(private val repository: AppRepository) {

    operator fun invoke(): List<Category> {
        return repository.getAllCategories()
    }
}