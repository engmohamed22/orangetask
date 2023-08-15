package com.example.orangetask.news.repository

import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.data.api.NewsService
import com.example.orangetask.data.db.ArticleDao
import com.example.orangetask.data.db.ArticleDao_Impl
import com.example.orangetask.models.NewsReppons
import com.example.orangetask.utils.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response

@RunWith(JUnit4::class)
class NewsRepositoryTest {
    private lateinit var newsApi: NewsService
    private lateinit var articleDao: ArticleDao
    private lateinit var repository: NewsRepository
    private val newsModel = NewsReppons(emptyList(),"0",1)
    private val API_KEY = "cadc8f8755f043cc825a345ab84b565c"
    private val newsResponse = Resource.Success(newsModel)
    private val errorResponse = Resource.Error<NewsReppons>(message = newsModel.status)


    @Before
    fun setUp() {
        newsApi = mock()
        val mockException: HttpException = mock()
        whenever(mockException.code()).thenReturn(401)
        runBlocking {
            // correct api query  param call
            whenever(newsApi.getEverything(
                "",
                1,
                API_KEY,
                )).thenReturn(Response.success(newsModel))

            // wrong api query  param call
            whenever(newsApi.getEverything(  "",
                1,
                API_KEY,)).thenThrow(mockException)
        }
        repository = NewsRepository(
            newsApi,articleDao)
    }

    @Test
    fun `test getWeather when valid search is requested, then weather is returned`() =
        runBlocking {
            assertEquals(newsResponse, repository.getSearchNews("success" , 1))
        }

    @Test
    fun `test getWeather when invalid search is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorResponse, repository.getSearchNews("Success" , 1))
        }
}