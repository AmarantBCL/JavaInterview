package com.amarant.apps.javainterview.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.amarant.apps.javainterview.domain.AppRepository
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question

class AppRepositoryImpl(private val application: Application) : AppRepository {

    private val dao = AppDatabase.getInstance(application).appDao()
    private val mapper = AppMapper()

    override fun getAllCategories(): LiveData<Category> {
        return dao.getAllCategories().map {
            mapper.mapCategoryDbModelToEntity(it)
        }
    }

    override fun getQuestionByCategory(id: Int): LiveData<Question> {
        return dao.getQuestionsByCategory(id).map {
            mapper.mapQuestionDbModelToEntity(it)
        }
    }
}