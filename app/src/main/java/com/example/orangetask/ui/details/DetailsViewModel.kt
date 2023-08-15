package com.example.orangetask.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import com.example.orangetask.R
import com.example.orangetask.data.api.NewsRepository
import com.example.orangetask.models.Article
import com.example.orangetask.utils.ResourceProvider
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val mResourceProvider: ResourceProvider
) : ViewModel() {

    private val statusMessage = MutableLiveData<Event<String>>()

    val message: LiveData<Event<String>>
        get() = statusMessage

    val isFavorite by lazy {
        MutableLiveData(0)
    }

    fun favoritesCheck(article: Article) {
        viewModelScope.launch {
            isFavorite.value = repository.find(article)
        }
    }

    fun favoriteAddAndCheck(article: Article) {
        viewModelScope.launch {
            if (isFavorite.value == 0) {
                repository.addToFavorite(article)
                statusMessage.value = Event(mResourceProvider.getString(R.string.successfully_add))
            } else {
                repository.deleteFromFavorite(article)
                statusMessage.value =
                    Event(mResourceProvider.getString(R.string.successfully_deleted))
            }
            favoritesCheck(article)
        }
    }
}