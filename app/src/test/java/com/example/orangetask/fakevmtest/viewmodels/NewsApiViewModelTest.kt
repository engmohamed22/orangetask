package com.example.orangetask.fakevmtest.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.orangetask.fakevmtest.di.fakeRepositoryModule
import com.example.orangetask.fakevmtest.getOrAwaitValueTest
import com.example.orangetask.fakevmtest.repository.FakeNewsApiRepository
import com.example.orangetask.fakevmtest.sample.dummyArticlesTest
import com.google.common.truth.Truth.assertThat
import  com.example.orangetask.ui.main.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.koin.core.logger.Level
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject

@ExperimentalCoroutinesApi
class NewsApiVMTest: KoinTest {
    private val viewModel by inject<MainViewModel>()
    private val repository by inject<FakeNewsApiRepository>()

    @get:Rule
    val setupViewModel = KoinTestRule.create {
        printLogger(Level.DEBUG)
        modules(fakeRepositoryModule)
    }

    @get:Rule
    var executionRule = InstantTaskExecutorRule()

    @Test
    fun `network error populates errorMessage`() = runBlockingTest {
        repository.setNetworkErrorHappened(true)
        viewModel.newsLiveData
    }

    @Test
    fun `network success populates newsData`() = runBlockingTest {
        repository.setNetworkErrorHappened(false)
        viewModel.newsLiveData
        val err = viewModel.newsLiveData.getOrAwaitValueTest()

        val newsData =dummyArticlesTest
        assertThat(err).isEqualTo(newsData)
    }
}