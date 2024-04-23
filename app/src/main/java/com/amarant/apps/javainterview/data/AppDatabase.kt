package com.amarant.apps.javainterview.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteStatement
import com.amarant.apps.javainterview.domain.ProgressColor
import com.amarant.apps.javainterview.presentation.JavaInterviewApp
import com.google.gson.Gson

@Database(entities = [CategoryDbModel::class, QuestionDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao(): AppDao

    companion object {

        private var INSTANCE: AppDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "java_interview.db"
        private const val DATA_FILE_NAME_CATEGORIES = "category_data.json"
        private const val DATA_FILE_NAME_QUESTIONS = "question_data.json"
        private const val INSERT_QUERY_CATEGORIES = "INSERT INTO categories (id, title) VALUES (?, ?)"
        private const val INSERT_QUERY_QUESTIONS = "INSERT INTO questions (id, categoryId, header, description, answer, isAnswered, progressColor) VALUES (?, ?, ?, ?, ?, ?, ?)"

        fun getInstance(application: Application): AppDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    AppDatabase::class.java,
                    DB_NAME
                ).addCallback(databaseCallback).build()
                INSTANCE = db
                return db
            }
        }

        private val databaseCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                readInternalFileData(DATA_FILE_NAME_CATEGORIES, db)
                readInternalFileData(DATA_FILE_NAME_QUESTIONS, db)
            }
        }

        private fun readInternalFileData(dataFileName: String, db: SupportSQLiteDatabase) {
            val context = JavaInterviewApp.instance.applicationContext
            val gson = Gson()
            val inputStream = context.assets.open(dataFileName)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val insertStatement: SupportSQLiteStatement
            if (dataFileName == DATA_FILE_NAME_CATEGORIES) {
                val dataList = gson.fromJson(jsonString, Array<CategoryDbModel>::class.java).toList()
                insertStatement = db.compileStatement(INSERT_QUERY_CATEGORIES)
                db.beginTransaction()
                try {
                    for (element in dataList) {
                        insertStatement.bindLong(1, element.id.toLong())
                        insertStatement.bindString(2, element.title)
                        insertStatement.executeInsert()
                    }
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            } else {
                val dataList = gson.fromJson(jsonString, Array<QuestionDbModel>::class.java).toList()
                insertStatement = db.compileStatement(INSERT_QUERY_QUESTIONS)
                db.beginTransaction()
                try {
                    for (element in dataList) {
                        insertStatement.bindLong(1, element.id.toLong())
                        insertStatement.bindLong(2, element.categoryId.toLong())
                        insertStatement.bindString(3, element.header)
                        insertStatement.bindString(4, element.description)
                        insertStatement.bindString(5, element.answer)
                        insertStatement.bindLong(6, 0.toLong())
                        insertStatement.bindLong(7, 0.toLong())
                        insertStatement.executeInsert()
                    }
                    db.setTransactionSuccessful()
                } finally {
                    db.endTransaction()
                }
            }
        }
    }
}