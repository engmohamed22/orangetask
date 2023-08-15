package com.example.orangetask.fakevmtest.di

import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.data.api.NewsService
import com.example.orangetask.fakevmtest.repository.FakeNewsApiRepository
import com.example.orangetask.models.Article

import com.example.orangetask.ui.main.MainViewModel
import com.example.orangetask.utils.ResourceProvider
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
private var newService:NewsService = TODO()
private var article:Article

val fakeRepositoryModule = module {
    single { FakeNewsApiRepository(newsService= newService,
        article= article
    ) }
}