package com.example.newsapp.ui.screen

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.newsapp.BottomMenuScreen
import com.example.newsapp.R
import com.example.newsapp.models.getAllArticlesCategory
import com.example.newsapp.network.NewsManager

@Composable
fun Categories(onFetchCategory: (String) -> Unit = {}, newsManager: NewsManager){
    val tabItems = getAllArticlesCategory()

    Column{
        LazyRow{
            items(tabItems.size){
                val category = tabItems[it]
                CategoryTab(
                    category = category.categoryName,
                    onFetchCategory = onFetchCategory,
                    isSelected = newsManager.selectedCategory.value == category
                )
            }
        }
    }
}



@Composable
fun CategoryTab(
    category: String,
    isSelected: Boolean = false,
    onFetchCategory: (String) -> Unit
){
    val background = if (isSelected) colorResource(id = R.color.purple_500) else colorResource(id = R.color.gray)
    Surface(
        modifier = Modifier
            .padding(horizontal = 5.dp, vertical = 16.dp)
            .clickable { onFetchCategory(category) },
        shape = MaterialTheme.shapes.large,
        color = background
    ) {
        Text(
            text = category,
            style = MaterialTheme.typography.body2,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(start = 16.dp, end=16.dp, top=20.dp, bottom = 20.dp)
        )
    }
}