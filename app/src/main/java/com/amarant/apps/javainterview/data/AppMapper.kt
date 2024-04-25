package com.amarant.apps.javainterview.data

import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.ProgressColor
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

    fun mapQuestionToDbModel(entity: Question): QuestionDbModel {
        return QuestionDbModel(
            entity.id,
            entity.categoryId,
            entity.header,
            entity.description,
            entity.answer,
            entity.isAnswered,
            entity.progressColor.id
        )
    }

    private fun mapCategoryDbModelToEntity(model: CategoryDbModel): Category {
        return Category(
            model.id,
            model.title
        )
    }

    private fun mapQuestionDbModelToEntity(model: QuestionDbModel): Question {
        return Question(
            model.id,
            model.categoryId,
            model.header,
            model.description,
            model.answer,
            model.isAnswered,
            ProgressColor.fromId(model.progressColor)
        )
    }
}