package com.example.orangetask.fakevmtest.sample


import  com.example.orangetask.models.Article

 import  com.example.orangetask.models.NewsReppons

val dummyArticleTest = Article(
        "someId",
        "Some Name",
   "JohnDoe",
    "Some content",
    "Some Desc",
    "2021-06-12T01:53:53Z",
    "Some title",

)

val dummyArticlesTest = arrayListOf(1, 2, 3, 4).map {
    dummyArticleTest.copy(title = "Title $it")
}