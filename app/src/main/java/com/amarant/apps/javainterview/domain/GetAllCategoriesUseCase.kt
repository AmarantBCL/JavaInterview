package com.amarant.apps.javainterview.domain

import androidx.lifecycle.LiveData

class GetAllCategoriesUseCase(private val repository: AppRepository) {

    operator fun invoke(): LiveData<Category> {
        return repository.getAllCategories()
    }
}