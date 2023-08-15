package com.example.orangetask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orangetask.data.db.ArticleDao
import  com.example.orangetask.models.Article

@Database(entities = [Article::class], version = 1, exportSchema = true)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

}