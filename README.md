# orangetask Android mobile app 
consumes the News API - https://newsapi.org/ . App Home screen displays the latest news
-mobile app that can display the latest news from various news sources (NDTV, CNN, Times, BBC, etc.) in one single place. The app should also be able to display the details of the news article.
Features -
Users can search for breaking news
Users can click on articles, it will open the article on a webview .
RecyclerView uses pagination to optimize api calls
This repository demonstrates MVVM and consumes free news api .
Caching Data in Favourites
Architecture - Mvvm , ViewModel & ROOM
Hilt - dependency injection library .
Glide for image loading
pretty time - format time
Solid priciple
Room - Access your app's SQLite database with in-app objects and compile-time checks.
Design Pattren
Network - Retrofit & Coroutines
Unit Test - KoTest-miockto test
Note - Bookmark function & List Ui is not done. Review team can check app build through apk folder & shreenshot folder
Architecture
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture. 
![81968739-a8bec700-95d1-11ea-8682-48fe879c25ff](https://github.com/engmohamed22/orangetask/assets/28671182/f373aa31-e4b7-4c39-a4a0-a14caf84e44b)
