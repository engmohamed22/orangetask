package com.example.orangetask.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import  com.example.orangetask.models.Article

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Article)

    @Delete
    suspend fun delete(article: Article)

    @Query("SELECT COUNT(publishedAt) FROM article")
    fun getCount(): LiveData<Int>

    @Query("SELECT COUNT(*) FROM article WHERE publishedAt=:publish")
    suspend fun find(publish: String): Int
}