package com.example.orangetask.data.api

import androidx.lifecycle.LiveData
import com.example.orangetask.data.db.ArticleDao
import com.example.orangetask.models.Article
import com.example.orangetask.models.NewsReppons
import retrofit2.Response
import javax.inject.Inject

open class NewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleDao: ArticleDao
) {
    suspend fun getNews(query: String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)
    suspend fun getSearchNews(query: String, pageNumber: Int) =
        newsService.getEverything(query = query, page = pageNumber)

    fun getFavoriteArticles(): LiveData<List<Article>> = articleDao.getAllArticles()

    suspend fun addToFavorite(article: Article) = articleDao.insert(article = article)
    suspend fun deleteFromFavorite(article: Article) = articleDao.delete(article = article)

    fun getCount(): LiveData<Int> {
        return articleDao.getCount()
    }

    suspend fun find(article: Article) = articleDao.find(article.publishedAt)
}