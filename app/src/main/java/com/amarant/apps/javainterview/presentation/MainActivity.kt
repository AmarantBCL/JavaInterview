package com.amarant.apps.javainterview.presentation

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Adapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amarant.apps.javainterview.R
import com.amarant.apps.javainterview.databinding.ActivityMainBinding
import com.amarant.apps.javainterview.domain.Category
import com.amarant.apps.javainterview.domain.Question
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navigateToCategoryFragment()
    }

    private fun navigateToCategoryFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, CategoryFragment())
            .commit()
    }
}