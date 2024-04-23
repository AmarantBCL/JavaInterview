package com.amarant.apps.javainterview.domain

enum class ProgressColor(val id: Int) {

    DIFFICULT(0), MEDIUM(1), EASY(2);

    companion object {
        fun fromId(id: Int): ProgressColor {
            return entries.find { it.id == id } ?: DIFFICULT
        }
    }
}