package com.amarant.apps.javainterview.data

import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question

class AppMapper {

    fun mapCategoryDbModelToEntity(model: CategoryDbModel): Category {
        return Category(
            model.id,
            model.title,
            model.numberOfQuestions
        )
    }

    fun mapQuestionDbModelToEntity(model: QuestionDbModel): Question {
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