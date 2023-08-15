package com.example.orangetask.models

import com.example.orangetask.models.Article

data class NewsReppons(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)