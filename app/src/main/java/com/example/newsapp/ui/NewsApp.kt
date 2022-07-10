package com.example.newsapp.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.components.BottomMenu
import com.example.newsapp.models.MockData
import com.example.newsapp.models.NewsData
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
        Navigation(navController = navController, scrollState= scrollState)
    }
}


@Composable
fun Navigation(navController: NavHostController, scrollState: ScrollState){
    NavHost(navController = navController, startDestination = "TopNews"){

        bottomNavigation(navController)

        composable("TopNews"){
            TopNews(navController = navController)
        }

        composable("DetailScreen/{id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})){
            NavBackStackEntry ->
            val id = NavBackStackEntry.arguments?.getInt("id")
            val newsData = MockData.getNews(id)
            DetailScreen(navController = navController, newsData, scrollState)
        }

    }
}


fun NavGraphBuilder.bottomNavigation(navController: NavController){
    composable(BottomMenuScreen.TopNews.route){
        TopNews(navController = navController)
    }

    composable(BottomMenuScreen.Categories.route){
        Categories()
    }

    composable(BottomMenuScreen.Sources.route){
        Sources()
    }
}