package com.amarant.apps.javainterview.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.amarant.apps.javainterview.domain.AppRepository
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question

class AppRepositoryImpl(private val application: Application) : AppRepository {

    private val dao = AppDatabase.getInstance(application).appDao()
    private val mapper = AppMapper()

    override fun getAllCategories(): LiveData<List<Category>> {
        return dao.getAllCategories().map {
            mapper.mapCategoryDbModelListToEntityList(it)
        }
    }

    override fun getQuestionByCategory(id: Int): LiveData<List<Question>> {
        return dao.getQuestionsByCategory(id).map {
            mapper.mapQuestionDbModelListToEntityList(it)
        }
    }
}