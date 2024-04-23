package com.amarant.apps.javainterview.data

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDbModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String
)
