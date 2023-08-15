package com.example.orangetask.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.models.NewsReppons
import com.example.orangetask.utils.Resource
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private val _searchNewsLiveData = MutableLiveData<Resource<NewsReppons>>()

    val searchNewsLiveData: LiveData<Resource<NewsReppons>>
        get() = _searchNewsLiveData

    private val searchNewsPage = 1

    init {
        getSearchNews(query = "")
    }

    fun getSearchNews(query: String) =
        viewModelScope.launch {
            _searchNewsLiveData.postValue(Resource.Loading())
            val response = repository.getSearchNews(query = query, pageNumber = searchNewsPage)
            if (response.isSuccessful) {
                response.body().let { res ->
                    _searchNewsLiveData.postValue(Resource.Success(res))
                }
            } else {
                _searchNewsLiveData.postValue(Resource.Error(message = response.message()))
            }
        }
}