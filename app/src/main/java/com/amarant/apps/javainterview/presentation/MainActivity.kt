package com.amarant.apps.javainterview.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amarant.apps.javainterview.R
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory(application))[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.getAllCategories().observe(this) {
            val categoryInputStream = assets.open("category_data.json")
            val categoryJsonString = categoryInputStream.bufferedReader().use { it.readText() }

            val questionInputStream = assets.open("question_data.json")
            val questionJsonString = questionInputStream.bufferedReader().use { it.readText() }

            val gson = Gson()
            val categoryArray = gson.fromJson(categoryJsonString, Array<Category>::class.java)
            val categories = categoryArray.toList()

            val questionArray = gson.fromJson(questionJsonString, Array<Question>::class.java)
            val questions = questionArray.toList()

            val resId =
                resources.getIdentifier("string/${categories.first().title}", "string", packageName)
            Log.e("WTF", resources.getString(resId))
            val question = questions.first()
            val headerId =
                resources.getIdentifier("string/${question.header}", "string", packageName)
            val descId =
                resources.getIdentifier("string/${question.description}", "string", packageName)
            val answerId =
                resources.getIdentifier("string/${question.answer}", "string", packageName)
            Log.d(
                "WTF",
                "${resources.getString(headerId)}\n\n${resources.getString(descId)}\n\n${
                    resources.getString(answerId)
                }"
            )
        }
    }
}