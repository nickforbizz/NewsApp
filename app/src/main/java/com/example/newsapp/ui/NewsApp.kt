package com.example.newsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.models.MockData
import com.example.newsapp.models.NewsData
import com.example.newsapp.ui.screen.DetailScreen
import com.example.newsapp.ui.screen.TopNews

@Composable
fun NewsApp(){
    Navigation()
}


@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "TopNews"){
        composable("TopNews"){
            TopNews(navController = navController)
        }
        composable("DetailScreen/{id}",
            arguments = listOf(navArgument("id"){type = NavType.IntType})){
            NavBackStackEntry ->
            val id = NavBackStackEntry.arguments?.getInt("id")
            val newsData = MockData.getNews(id)
            DetailScreen(navController = navController, newsData)
        }
    }
}