package com.amarant.apps.javainterview.presentation

import android.app.Application

class JavaInterviewApp : Application() {

    companion object {
        lateinit var instance: JavaInterviewApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}