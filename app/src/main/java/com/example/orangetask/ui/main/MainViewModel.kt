package com.example.orangetask.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.orangetask.R
import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.data.db.ArticleDao
import com.example.orangetask.models.Article
import com.example.orangetask.models.NewsReppons
import com.example.orangetask.utils.Resource
import com.example.orangetask.utils.ResourceProvider
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: NewsRepository,
    mResourceProvider: ResourceProvider,
) : ViewModel() {

    private val _newsLideData = MutableLiveData<Resource<NewsReppons>>()

    val newsLiveData: LiveData<Resource<NewsReppons>>
        get() = _newsLideData

    private val newsPage = 1

    init {
        getNews(mResourceProvider.getString(R.string.region_popular_news))
    }

    private fun getNews(query: String) =
        viewModelScope.launch {
            _newsLideData.postValue(Resource.Loading())
            val response = repository.getNews(
                query = query,
                pageNumber = newsPage
            )
            if (response.isSuccessful) {
                response.body().let { res ->
                    _newsLideData.postValue(Resource.Success(res))
                }
            } else {
                _newsLideData.postValue(Resource.Error(message = response.message()))
            }
        }

}