package com.example.newsapp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Source
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuScreen(
    val route: String,
    val icon: ImageVector,
    val title: String
){

    object  TopNews: BottomMenuScreen(
        route = "top_news",
        title = "Top News",
        icon= Icons.Outlined.Home,
    )

    object  Categories: BottomMenuScreen(
        route = "categories",
        title = "Categories",
        icon= Icons.Outlined.Category,
    )

    object  Sources: BottomMenuScreen(
        route = "sources",
        title = "Sources",
        icon= Icons.Outlined.Source,
    )
}
