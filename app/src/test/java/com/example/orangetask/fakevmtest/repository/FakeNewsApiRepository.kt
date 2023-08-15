package com.example.orangetask.fakevmtest.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.orangetask.utils.Resource
import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.data.api.NewsService
import com.example.orangetask.data.db.ArticleDao
import  com.example.orangetask.models.Article


class FakeNewsApiRepository(newsService: NewsService,
                            article: ArticleDao
) : NewsRepository(newsService, article) {

    private var articles = Article("","","","","","","")
    private val successResource = Resource.Success(Article("","","","","","",""));
   // private val errorResource = Resource.Error(message = response.message())

    private val observableArticles = MutableLiveData<Resource<Article>>(successResource)
    private var networkErrorHappened = false

    fun setNetworkErrorHappened(hasError: Boolean) {
        networkErrorHappened = hasError

    }

    suspend fun getHeadlines(
        query: String,
        page: Int,
        pageSize: Int
    ): Resource<Article>? {
        TODO("Not yet implemented")
    }

    fun observeHeadlines(): LiveData<Resource<Article>> {
        TODO("Not yet implemented")
    }


}