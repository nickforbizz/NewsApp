package com.example.newsapp.ui

import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.components.BottomMenu
import com.example.newsapp.models.TopNewsArticle
import com.example.newsapp.network.NewsManager
import com.example.newsapp.ui.screen.Categories
import com.example.newsapp.ui.screen.DetailScreen
import com.example.newsapp.ui.screen.Sources
import com.example.newsapp.ui.screen.TopNews

@Composable
fun NewsApp(){
    val navController = rememberNavController()
    val scrollState  = rememberScrollState()
    MainScreen(navController, scrollState)
}



@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState){
    Scaffold(bottomBar = {
        BottomMenu(navController)
    }) {
        Navigation(navController = navController, scrollState= scrollState, paddingValues = it)
    }
}


@Composable
fun Navigation(navController: NavHostController,
               scrollState: ScrollState,
               newsManager: NewsManager = NewsManager(),
                paddingValues: PaddingValues
){


    val articles = mutableListOf(TopNewsArticle())
    articles.addAll(newsManager.newsResponse.value.articles ?: listOf(TopNewsArticle()))
    Log.d("articles", "${articles}")

    articles.let {
        NavHost(navController = navController,
            startDestination = BottomMenuScreen.TopNews.route, modifier = Modifier.padding(paddingValues)){

            bottomNavigation(navController, articles, newsManager)


            composable("DetailScreen/{index}",
                arguments = listOf(navArgument("index"){type = NavType.IntType})){
                    NavBackStackEntry ->
                val index = NavBackStackEntry.arguments?.getInt("index")
                index?.let {

                    if (newsManager.q.value.isNotEmpty()){
                        articles.clear()
                        articles.addAll(newsManager.searchedNewsResponse.value.articles ?: listOf())
                    }else{
                        articles.clear()
                        articles.addAll(newsManager.newsResponse.value.articles ?: listOf())
                    }
                    val article = articles[index]
                    DetailScreen(navController = navController, article, scrollState)
                }

            }

        }
    }

}


fun NavGraphBuilder.bottomNavigation(navController: NavController, articles: List<TopNewsArticle>, newsManager: NewsManager){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController, articles, newsManager.q, newsManager)
    }

    composable(BottomMenuScreen.Categories.route){
        newsManager.onSelectedCategoryChanged("business")
        newsManager.getArticlesByCategory("business")

        Categories(newsManager = newsManager, onFetchCategory = {
            Log.d("categoryC", "changed to $it")
            newsManager.onSelectedCategoryChanged(it)
            newsManager.getArticlesByCategory(it)
        })
    }

    composable(BottomMenuScreen.Sources.route){
        Sources(newsManager = newsManager)
    }
}