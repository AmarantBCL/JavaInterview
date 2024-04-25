package com.amarant.apps.javainterview.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import com.amarant.apps.javainterview.domain.AppRepository
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppRepositoryImpl(private val application: Application) : AppRepository {

    private val dao = AppDatabase.getInstance(application).appDao()
    private val mapper = AppMapper()

    override fun getAllCategories(): LiveData<List<Category>> {
        val resultLiveData = MediatorLiveData<List<Category>>()
        resultLiveData.addSource(dao.getAllCategories()) { categories ->
            val categoryEntities = mapper.mapCategoryDbModelListToEntityList(categories)
            val questionsLiveData = dao.getAllQuestions()
            resultLiveData.addSource(questionsLiveData) { questions ->
                val categoryMap = categoryEntities.associateBy { it.id }
                questions.forEach { question ->
                    val categoryId = question.categoryId
                    val category = categoryMap[categoryId]
                    if (category != null) {
                        category.maxQuestions++
                        if (question.isAnswered) {
                            category.numberOfQuestions++
                        }
                    }
                }
                resultLiveData.value = categoryEntities
            }
        }
        return resultLiveData
    }

    override fun getQuestionByCategory(id: Int): LiveData<List<Question>> {
        return dao.getQuestionsByCategory(id).map {
            mapper.mapQuestionDbModelListToEntityList(it)
        }
    }

    override suspend fun setQuestionAnswered(question: Question) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.setQuestionAnswered(mapper.mapQuestionToDbModel(question))
        }
    }
}