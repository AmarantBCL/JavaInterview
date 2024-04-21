package com.amarant.apps.javainterview.data

import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question

class AppMapper {

    fun mapCategoryDbModelListToEntityList(list: List<CategoryDbModel>): List<Category> {
        return list.map {
            mapCategoryDbModelToEntity(it)
        }
    }

    fun mapQuestionDbModelListToEntityList(list: List<QuestionDbModel>): List<Question> {
        return list.map {
            mapQuestionDbModelToEntity(it)
        }
    }

    private fun mapCategoryDbModelToEntity(model: CategoryDbModel): Category {
        return Category(
            model.id,
            model.title,
            model.numberOfQuestions
        )
    }

    private fun mapQuestionDbModelToEntity(model: QuestionDbModel): Question {
        return Question(
            model.id,
            model.categoryId,
            model.header,
            model.description,
            model.answer,
            model.isAnswered
        )
    }
}