package com.robo.news.viewmodel.home.HomeViewModelTest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.data.api.NewsService
import com.example.orangetask.data.db.ArticleDao
import com.example.orangetask.models.Article
import com.example.orangetask.models.NewsReppons
import com.example.orangetask.ui.main.MainViewModel
import com.example.orangetask.utils.Resource
import com.example.orangetask.utils.ResourceProvider
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.timeout
import com.nhaarman.mockitokotlin2.verify

import com.nhaarman.mockitokotlin2.whenever


import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import retrofit2.Response

@RunWith(JUnit4::class)
internal class HomeViewModelTest {
    private lateinit var viewModel: MainViewModel
    private lateinit var newsRepository: NewsRepository
    private lateinit var mResourceProvider: ResourceProvider
    private lateinit var newsObserver: Observer<Resource<NewsReppons>>
    private val newsModel = NewsReppons(emptyList(),"0",1)
    private val successResource = Resource.Success((NewsReppons(emptyList(),"0",1)));
   // private val errorResource = NetworkResponse.Failure("Error 404", Exception())


    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

        @ObsoleteCoroutinesApi
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
        newsRepository = mock()
        runBlocking {
            whenever(newsRepository.getNews("Success",1)).thenReturn(Response.success(newsModel))
        //    whenever(newsRepository.getHeadlines("Error",1,5)).thenReturn(errorResource)
        }
        viewModel = MainViewModel(newsRepository,mResourceProvider)
        newsObserver = mock()
    }

    @ObsoleteCoroutinesApi
    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `when getHeadlines is called with valid result, then observer is updated with success`() = runBlocking {
        viewModel.newsLiveData.observeForever(newsObserver)
        delay(10)
        verify(newsRepository).getNews("Success",1)
         verify(newsObserver, timeout(50)).onChanged(successResource)
    }

    @Test
    fun `when getHeadlines is called with invalid result, then observer is updated with failure`() = runBlocking {
        viewModel.newsLiveData.observeForever(newsObserver)
        delay(10)
        verify(newsRepository).getNews("Error",1)
   //    verify(newsObserver, timeout(50)).onChanged(errorResource)
    }
}